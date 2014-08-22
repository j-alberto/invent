package org.jar.invent.core.service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;

import org.jar.invent.business.ItemRegisterBR;
import org.jar.invent.core.domain.ItemEntity;
import org.jar.invent.core.domain.converter.ListConverter;
import org.jar.invent.util.ImageUtils;
import org.jar.invent.web.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImp implements InventoryService {
	@Autowired
	private ItemRegisterBR itemRegister;
	@Autowired
	private	ConversionService conversionService;
	@Autowired
	private	ListConverter listConverter;
	@Autowired
	private ImageUtils imageUtils;
	
	private static final int SNAPHOT_IMAGE_HEIGHT = 50;
	
	public void setItemRegister(ItemRegisterBR itemRegister) {
		this.itemRegister = itemRegister;
	}
	public void setTransformService( ConversionService conversionService) {
		this.conversionService = conversionService;
	}
	public void setListConverter(ListConverter listConverter) {
		this.listConverter = listConverter;
	}
	
	@Override
	public Item registerNewItem(Item item) {
		try {
			saveImagesToDisk(item);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}

		ItemEntity itemEntity = conversionService.convert(item, ItemEntity.class);
		itemEntity = itemRegister.registerNewItem(itemEntity);
		
		return conversionService.convert(itemEntity, Item.class);
	}

	@Override
	public boolean deleteItem(Item item) {
		ItemEntity itemEntity = conversionService.convert(item, ItemEntity.class);
		itemRegister.deleteItem(itemEntity);
		
		return true;
	}

	@Override
	public boolean modifyItem(Item item){
		try {
			saveImagesToDisk(item);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}

		ItemEntity itemEntity = conversionService.convert(item, ItemEntity.class);
		itemRegister.modifyItem(itemEntity);
		
		return true;
	}

	@Override
	public Page<Item> getItems(String desc, Pageable pageRequest) {

		Page<ItemEntity> itemsPage = itemRegister.getItems(desc, pageRequest);
		List<Item> itemsWeb = listConverter.convert(itemsPage.getContent(), Item.class);
		
		return new PageImpl<Item>(itemsWeb, pageRequest, itemsPage.getTotalElements());
	}
	@Override
	public Item getItem(int id) {
		ItemEntity item = itemRegister.getItem(id);
		return conversionService.convert(item, Item.class);
	}

	// TODO move to helper/utility bean for file processing
	/**
	 * Saves images linked to this item, as well as snapshots from each image. Fills urlImage and urlSnapshot fields
	 * in Item object with the relative paths of such images. 
	 * @param item Contains images to save as multipart files
	 * @throws IllegalStateException
	 * @throws IOException
	 */
    private void saveImagesToDisk(Item item) throws IllegalStateException, IOException {
    	if(null == item.getImage() || item.getImage().isEmpty()){
    		return;
    	}else if(item.getImage().getContentType().contains("image")){
    		
    		Path path = FileSystems.getDefault().getPath("itemImages"
					,String.valueOf(item.getId())
					,item.getImage().getOriginalFilename());

	        imageUtils.saveImage(item.getImage(), path);
	        item.setUrlImage(path.toString());
	        
			Path pathSnapshot = FileSystems.getDefault().getPath("itemImages"
					,String.valueOf(item.getId())
					,"snapshot"
					,item.getImage().getOriginalFilename());

			BufferedImage snapshot = imageUtils.createSnapshot(path.toFile(), SNAPHOT_IMAGE_HEIGHT);
			
			imageUtils.saveImage(snapshot, pathSnapshot);
			item.setUrlSnapshot(pathSnapshot.toString());
			
    	}
    }


    public void setImageUtils(ImageUtils imageUtils){
    	this.imageUtils = imageUtils;
    }
}

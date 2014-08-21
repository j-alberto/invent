package org.jar.invent.core.service;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilePermission;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;

import org.jar.invent.business.ItemRegisterBR;
import org.jar.invent.core.domain.ItemEntity;
import org.jar.invent.core.domain.converter.ListConverter;
import org.jar.invent.web.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class InventoryServiceImp implements InventoryService {
	@Autowired
	private ItemRegisterBR itemRegister;
	@Autowired
	private	ConversionService conversionService;
	@Autowired
	private	ListConverter listConverter;
	
	
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
			saveMultipartToDisk(item);
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
			saveMultipartToDisk(item);
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
    private void saveMultipartToDisk(Item item) throws IllegalStateException, IOException {
    	if(null == item.getImage() || item.getImage().isEmpty()){
    		return;
    	}else if(item.getImage().getContentType().contains("image")){
    		
    		Path path = FileSystems.getDefault().getPath("itemImages"
					,String.valueOf(item.getId())
					,item.getImage().getOriginalFilename());
    		
			if(Files.notExists(path.getParent())){
				Files.createDirectories(path.getParent());
			}
			if(Files.notExists(path)){
				Files.createFile(path);
			}
			
			File f = path.toFile().getAbsoluteFile();
	        item.getImage().transferTo(f);
	        
	        item.setUrlImage(path.toString());
	        
	        //snapshot
			BufferedImage s = ImageIO.read(f);
			BufferedImage s2 = resizeImage(s);
			
			Path path2 = FileSystems.getDefault().getPath("itemImages"
					,String.valueOf(item.getId())
					,"snapshot"
					,item.getImage().getOriginalFilename());
			if(Files.notExists(path2.getParent())){
				Files.createDirectories(path2.getParent());
			}
			if(Files.notExists(path2)){
				Files.createFile(path2);
			}
			
			ImageIO.write(s2, "png", path2.toFile());
			item.setUrlSnapshot(path2.toString());
			
    	}
    }
    
    private static BufferedImage resizeImage(BufferedImage originalImage){
    	 
		BufferedImage resizedImage = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, 50, 50, null);
		g.dispose();	
		//g.setComposite(AlphaComposite.Src);
	 
//		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
//		RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//		g.setRenderingHint(RenderingHints.KEY_RENDERING,
//		RenderingHints.VALUE_RENDER_QUALITY);
//		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//		RenderingHints.VALUE_ANTIALIAS_ON);
 
	return resizedImage;
    }
    
}

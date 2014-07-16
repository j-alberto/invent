$( window ).load(function() {
    $('#loadBox').fadeOut('fast');
   
});

/**
 * Loads content into the workspace, applying animation and 'loading...' message
 * @param url: Route to the content to load
 */
function loadPage(url){
	$('#loadBox').fadeIn('fast');
	$('#main').hide('normal',loadContent);
	
	function loadContent(){
		$('#main').load(url,'',showNewContent);
	}
	function showNewContent() {
        $('#loadBox').fadeOut('fast');
        $('#main').show('normal');
    	
	}
}

/**
 * calls fragments with corresponding data loaded
 */
function readCatalog(catalogName,containerName,withName) {
	if(catalogName == undefined || catalogName == null) return;
	if(containerName == undefined || containerName == null) return; else containerName = "#"+containerName;
	withName = withName || ""; withName = '/'+withName;

	var url = '/liber/catalogs/'+catalogName+'/list'+withName;

	$(containerName).load(url);
	
}


/**
 * Adds async capabilities to regular submit forms
 * @param element The submit button changing to async call
 */
function asyncSubmit(element,targetId){
	var myForm = $(element).parents('form');
	
	var options = { 
	        target:        '#'+targetId   // target element(s) to be updated with server response 
//	        beforeSubmit:  preProcessFunction, 
//	        success:       callbackFunction
	};

	myForm.submit(function() { 
        // inside event callbacks 'this' is the DOM element so we first 
        // wrap it in a jQuery object and then invoke ajaxSubmit 
        $(this).ajaxSubmit(options); 
        // always return false to prevent standard browser submit and page navigation 
        return false; 
    });

}

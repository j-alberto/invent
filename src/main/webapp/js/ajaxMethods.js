/**
 * Style for row clicked in tables
 */
$(document).ready(function(){
	$('table tbody tr').on('click', function(event) {
	    
	    $(this).addClass('info').siblings().removeClass('info');
	    
//	    $(this).siblings().remove('td button');
//	    
//	    var x = $("<button id='myButton'>edit</button>");
//	    $(this).children('td:last-child').append(x);

	    //console.log($(this).children('td:last-child').prop('class'));
	});
	
	$( document ).ajaxError(function( event, request, settings ) {
		  $( "#msg" ).append( "<li>Error requesting page " + settings.url + "</li>" );
		  console.log('error!!');
		  alert('sdf');
		});
	
});

/**
 * Loads content into the workspace, applying animation
 * @param url: url of  the content to load
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

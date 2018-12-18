/**
 * 
 */
$(window).resize(function(){ 
    $(".verticalCenter").css({ 
        position: "absolute", 
        left: ($(window).width() - $(".verticalCenter").outerWidth())/2, 
        top: ($(window).height() - $(".verticalCenter").outerHeight())/2
    }); 
    
    $(".productVerticalCenter").css({ 
        position: "absolute", 
        left: ($(window).width() - $(".productVerticalCenter").outerWidth())/2, 
        top: ($(window).height() - $(".productVerticalCenter").outerHeight())/2 
    });
}); 
	
$(function(){ 
	$(window).resize(); 
});
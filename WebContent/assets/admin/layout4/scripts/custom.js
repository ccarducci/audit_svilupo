// Aside Dx a comparsa
var contOne = true;
function slideAside(){
    sizeModalCustom();
    
    if(contOne){
        jQuery('.menu-sx-levelone-facility').animate({marginRight:'0'}, 400);
        jQuery('.modal-custom').css("display", "block");
        contOne = false;
    }else{
        jQuery('.menu-sx-levelone-facility').animate({marginRight:'-105%'}, 400);
        jQuery('.modal-custom').css("display", "none");
        contOne = true;
    }
};

function slideAsideNew(e){
    sizeModalCustom();
    
    if(contOne){
        if(e==''){
            jQuery('.menu-sx-levelone-facility.default').animate({marginRight:'0'}, 400);
            //console.log('OK');
        }else if(e=='add'){
            jQuery('.menu-sx-levelone-facility.add').animate({marginRight:'0'}, 400);
        }else if(e=='update'){
            jQuery('.menu-sx-levelone-facility.update').animate({marginRight:'0'}, 400);
        }else if(e=='display'){
            jQuery('.menu-sx-levelone-facility.display').animate({marginRight:'0'}, 400);
        }
        jQuery('.modal-custom').css("display", "block");
        contOne = false;
    }else{
        if(e==''){
            jQuery('.menu-sx-levelone-facility').animate({marginRight:'-105%'}, 400);
        }
        jQuery('.modal-custom').css("display", "none");
        contOne = true;
    }
};

function slideAsideInps(e){
    sizeModalCustom();
    
    if(contOne){
        //console.log(e);
        jQuery('.aside-dx-cruscottoAudit.' + e).animate({marginRight:'0'}, 400);
                
        jQuery('.modal-custom').css("display", "block");
        contOne = false;
    }else{
        if(e==''){
            jQuery('.aside-dx-cruscottoAudit').animate({marginRight:'-105%'}, 400);
        }
        jQuery('.modal-custom').css("display", "none");
        contOne = true;
    }
};

function sizeModalCustom(){
    jQuery('.modal-custom').css({
        width: jQuery(document).width(),
        height: jQuery(document).height()
    });
}

jQuery('.modal-custom').click(function(){
    slideAside();
});

function resizeModal(){
    //alert('Ok!');
    jQuery(window).resize(function(){
        jQuery('.menu-sx-levelone-facility').animate({marginRight:'-105%'}, 400);
        jQuery('.modal-custom').css("display", "none");
        contOne = true;
    });
}

function closePlay() {
    slideAside();
    slideAsideNew('');
}

// --------------------------------------------------------------

jQuery('.del').click(function(){
    bootbox.confirm("Vuoi eliminare il record?", function(result) {
        //Example.show("Confirm result: "+result);
    }); 
});

// --------------------------------------------------------------

// Slide Clienti 
var screenWidth = jQuery(document).width();
//console.log(screenWidth);
var portletMove = jQuery('.tab-content.wizard-flm .portlet-body');
var nSlide = jQuery('.wizard-flm .slide-4-box').length;
var nSlideMobile = nSlide * 2;
var goMargin = 930 + 'px';
var nbox = true;
var goMargin = 930 + 'px';
/*var goMargin1 = (435-35) + 'px';
var goMargin2 = (495+35) + 'px';*/

var goMargin1 = (435) + 'px';
var goMargin2 = (495) + 'px';
var cont = 0;

function initSlideClientiFLM(){
    console.log('nSlide: ' + nSlide);
    portletMove.css('width', 1024 * nSlide);
    //console.log('.tab-content.wizard-flm .portlet-body: ' + portletMove.width());
    nextClientiFLM();
}

function nextClientiFLM(){
    if(nSlide <= 1){
        jQuery('.prev-slide-clienti').css('display', 'none');
        jQuery('.next-slide-clienti').css('display', 'none');
    }else{
        jQuery('.next-slide-clienti').css('display', 'block');
    }
}

function slidePrevClientiFLMinit(){
    if(screenWidth > 1040){
        slidePrevClientiFLM(goMargin, goMargin, nSlide);
    }else{
        slidePrevClientiFLM(goMargin1, goMargin2, nSlideMobile);
    }
}

function slideNextClientiFLMinit(){
    if(screenWidth > 1040){
        slideNextClientiFLM(goMargin, goMargin, nSlide)
    }else{
        slideNextClientiFLM(goMargin1, goMargin2, nSlideMobile);
    }
}

function slidePrevClientiFLM(goMarginUno, goMarginDue, nslide){
    //console.log('nslide: ' + nslide);
    
    //if(portletMove.css('margin-left') < '0'){
    if(cont > 0){
        console.log('nbox: '+nbox);
        if(nbox){
            portletMove.animate({
                marginLeft: "+=" + goMarginDue
            }, 400,
            'linear',
            function() {
                jQuery('.next-slide-clienti').css('display', 'block');
                if(cont == 0){
                    jQuery('.prev-slide-clienti').css('display', 'none');
                }
            });
            cont--;
            //console.log('cont: ' + cont);
            nbox =false;
        }else{
            portletMove.animate({
                marginLeft: "+=" + goMarginUno
            }, 400,
            'linear',
            function() {
                jQuery('.next-slide-clienti').css('display', 'block');
                if(cont == 0){
                    jQuery('.prev-slide-clienti').css('display', 'none');
                }
            });
            cont--;
            //console.log('cont: ' + cont);
            nbox =true;
        }
    }
}

function slideNextClientiFLM(goMarginUno, goMarginDue, nslide){
    //console.log('nslide: ' + nslide);
    
    if(cont < (nslide - 1)){
        console.log('nbox: '+nbox);
        if(nbox){
            portletMove.animate({
                //marginLeft: '-=930px'
                marginLeft: '-=' + goMarginUno
            }, 400,
            'linear',
            function() {
                jQuery('.prev-slide-clienti').css('display', 'block');
                if(cont == (nslide - 1)){
                    jQuery('.next-slide-clienti').css('display', 'none');
                }
            });
            cont++;
            //console.log('cont: ' + cont);
            nbox = false;
        }else{
            portletMove.animate({
                //marginLeft: '-=930px'
                marginLeft: '-=' + goMarginDue
            }, 400,
            'linear',
            function() {
                jQuery('.prev-slide-clienti').css('display', 'block');
                if(cont == (nslide - 1)){
                    jQuery('.next-slide-clienti').css('display', 'none');
                }
            });
            cont++;
            //console.log('cont: ' + cont);
            nbox = true;
        }
    }
}

// --------------------------------------------------------------

//var contLike = true;
jQuery('span.like-sconto').click(function() {
    if($(this).children('.sconto-up.active-scont').length){
        $(event.target).parents('tr').find('td span.label-sm').html('Non attivo');
        $(event.target).parents('tr').find('td span.label-sm').addClass('label-danger');
    }else{
        $(event.target).parents('tr').find('td span.label-sm').html('Attivo');
        $(event.target).parents('tr').find('td span.label-sm').removeClass('label-danger');
        $(event.target).parents('tr').find('td span.label-sm').addClass('label-success');
    }
    $(this).children().toggleClass('active-scont');
});
$("#miodiv").length

// --------------------------------------------------------------

jQuery('.ripristino-auto input').click(function(){
    var id_radio = jQuery(this).attr('id');
    //alert(id_radio);
    if(id_radio == 'radio1'){
        $('.combo-codfat select').prop('disabled', false);
    }else{
        $('.combo-codfat select').prop('disabled', 'disabled');
    }
})

// --------------------------------------------------------------















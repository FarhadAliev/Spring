
var items = [];
$(document).ready(function(){

    $.ajax({
        url:"/slider",
        type : "GET",
        dataType: 'JSON',
        data : items,
        success: function(data) {
            $.each( data, function( key, val ) {
                items.push( "{'" + key + "' : '" + val + "'}" );
            });
        },
    })
});
$.getJSON( "/slider", function( items ) {
    console.log(items[0].productName);
});


var index = 0;
var count = items.length;
document.querySelector('.div-icon-1').addEventListener('click', function () {
    index--;
    
    if(index>0){
        showSlidePrev(index);
    }else{
        
        index=5;
        showSlidePrev(index);
    }

    
});

document.querySelector('.div-icon-2').addEventListener('click', function () {
    index++;
    if(index>5 ){
        index=0;
        showSlideNext(index);
        
    }else{
        showSlideNext(index);
       
    }
    
    
});



function showSlideNext(index) {

    document.querySelector('.title-z-1').textContent = obj[0+index].productName;
    document.querySelector('.text-z-1').textContent= obj[0+index].slary;
    document.querySelector('.img-z-1').setAttribute('src', obj[0+index].image);

    document.querySelector('.title-z-2').textContent = obj[1+index].name;
    document.querySelector('.text-z-2').textContent= obj[1+index].slary;
    document.querySelector('.img-z-2').setAttribute('src', obj[1+index].image);

    document.querySelector('.title-z-3').textContent = obj[2+index].name;
    document.querySelector('.text-z-3').textContent= obj[2+index].slary;
    document.querySelector('.img-z-3').setAttribute('src', obj[2+index].image);

    document.querySelector('.title-z-4').textContent = obj[3+index].name;
    document.querySelector('.text-z-4').textContent= obj[3+index].slary;
    document.querySelector('.img-z-4').setAttribute('src', obj[3+index].image);
}



function showSlidePrev(index) {

    document.querySelector('.title-z-1').textContent = product[index+0].name;
    document.querySelector('.text-z-1').textContent= product[index+0].slary;
    document.querySelector('.img-z-1').setAttribute('src', product[index+0].image);

    document.querySelector('.title-z-2').textContent = product[index+1].name;
    document.querySelector('.text-z-2').textContent= product[index+1].slary;
    document.querySelector('.img-z-2').setAttribute('src', product[index+1].image);

    document.querySelector('.title-z-3').textContent = product[index+2].name;
    document.querySelector('.text-z-3').textContent= product[index+2].slary;
    document.querySelector('.img-z-3').setAttribute('src', product[index+2].image);

    document.querySelector('.title-z-4').textContent = product[index+3].name;
    document.querySelector('.text-z-4').textContent= product[index+3].slary;
    document.querySelector('.img-z-4').setAttribute('src', product[index+3].image);
}













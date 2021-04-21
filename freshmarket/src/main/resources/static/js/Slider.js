

var obj =[];
$(document).ready(function(){

    $.getJSON('/slider',function(data){
        var i=0;
        for(i=0;i<data.length;i++){
            obj[i]=[String(data[i].productName),String(data[i].specialPrice),String(data[i].productImg)];
        }



        $('.title-z-1').text (obj[0][0]);
        $('.text-z-1').text(obj[0][1]);
        $('.img-z-1').attr('src', obj[0][2]);

        $('.title-z-2').text(obj[1][0]);
        $('.text-z-2').text(obj[1][1]);
        $('.img-z-2').attr('src', obj[1][2]);

        $('.title-z-3').text (obj[2][0]);
        $('.text-z-3').text(obj[2][1]);
        $('.img-z-3').attr('src', obj[2][2]);

        $('.title-z-4').text (obj[3][0]);
        $('.text-z-4').text(obj[3][1]);
        $('.img-z-4').attr('src', obj[3][2]);




var count = obj.length-1;
var a=0;
var b=1;
var c=2;
var d=3;

$('.div-icon-2').click(function() {
    a++;
    b++;
    c++;
    d++;
    if(a>count){
        a=0;
        showSlideNext(a,b,c,d);
    }
    if(b>count){
        b=0;
        showSlideNext(a,b,c,d);
    }
    if(c>count){
        c=0;
        showSlideNext(a,b,c,d);
    }
    if(d>count){
        d=0;
        showSlideNext(a,b,c,d);
    }

        showSlideNext(a,b,c,d);



});




function showSlideNext(a,b,c,d) {

    $('.title-z-1').text (obj[a][0]);
    $('.text-z-1').text(obj[a][1]);
    $('.img-z-1').attr('src', obj[a][2]);

    $('.title-z-2').text(obj[b][0]);
    $('.text-z-2').text(obj[b][1]);
    $('.img-z-2').attr('src', obj[b][2]);

    $('.title-z-3').text (obj[c][0]);
    $('.text-z-3').text(obj[c][1]);
    $('.img-z-3').attr('src', obj[c][2]);


    $('.title-z-4').text (obj[d][0]);
    $('.text-z-4').text(obj[d][1]);
    $('.img-z-4').attr('src', obj[d][2]);
}



        $('.div-icon-1').click(function() {
            a--;
            b--;
            c--;
            d--;

            if(a<0){
               a=count;
            }
            if(b<0){
                b=count;
            }
            if(c<0){
                c=count;
            }
            if(d<0){
                d=count;
            }


            showSlidePrev(a,b,c,d);



        });

function showSlidePrev(a,b,c,d) {

    $('.title-z-1').text(obj[a][0]);
    $('.text-z-1').text(obj[a][1]);
    $('.img-z-1').attr('src', obj[a][2]);

    $('.title-z-2').text(obj[b][0]);
    $('.text-z-2').text(obj[b][1]);
    $('.img-z-2').attr('src', obj[b][2]);

    $('.title-z-3').text(obj[c][0]);
    $('.text-z-3').text(obj[c][1]);
    $('.img-z-3').attr('src', obj[c][2]);

    $('.title-z-4').text(obj[d][0]);
    $('.text-z-4').text(obj[d][1]);
    $('.img-z-4').attr('src',obj[d][2]);
}





    });

});







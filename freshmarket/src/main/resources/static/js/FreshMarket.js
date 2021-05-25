var card;

$(document).ready(function() {
    $('.add-to-card').click(function (){
        var productName = $(this).next().val();
        var count=$(this).parent('.Grab-add-to-Cart').prev().children('.counter').children('.summ-products-input').val();
        console.log(productName)
        console.log(count);



        $.ajax({
            type: "GET",
            url: "/addToCart/"+productName+"/"+count,
            success: function (result) {
                JSON.stringify(result)

                console.log(result)
                $('.products-digit').text(result.length);

                // $('.view-img').html("")
                // var s = ""
                // s+='<img class="view-img" src="'+result[0].productImg+'" alt="">';
                // $('.a').html(s)


                 card=$('.card-img-view').attr("src",result[0].productImg);
                // console.log(result[0].productImg);
    }

        });
})
});


$(document).ready(function() {

    $('.linkProduct').mouseover(function () {
       var productName = $(this).next().next().children(".products-name").text()
        $(".linkProduct").attr("href","product?productName="+productName+"")

    })

});

$('.chat-logo').click(function () {
    $('.chat').show();
    $('.carousel-control-next-icon').css({
        "z-index": "-1",
        

    })
    $('.chat').css({
        "z-index": "1",
        

    })
});



$('.close-chat').click(function () {
    $('.chat').hide();
    $('.carousel-control-prev').css({
        "z-index": "none"

    })
    
   
});


// -------------------------------------------------------------------------

$('.plus').click(function () {
    const input = $(this).closest(".counter").find("input");
    input.val(+input.val() + 1);
});

$('.minus').click(function () {
    const input = $(this).closest(".counter").find("input");
    if (input.val() == 0) {
        input.val(0);
    } else {
        input.val(+input.val() - 1);
    }
});




// -----------------------------------------------------------------------


var allSite = $('.allSite');

$('.products-digit').click(function () {
    $('.allSite').css({
        "opacity": "0.2",
        "z-index": "-1",

    })


    $('.card-product-list').fadeIn(700);
    $('.card-product-list').animate({right: '0rem'});
});

$('.product-close').click(function () {

    $('.card-product-list').animate({right: '38rem'});
    $('.card-product-list').fadeOut("fast");

    $('.allSite').css({
        "opacity": "1",
        "z-index": "none",

    })

});




// -----------------------------------------------------------




$('.second-element-responsive').click(function () {
    $( '.second-element' ).toggle()

});





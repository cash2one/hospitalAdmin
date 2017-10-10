$(function(){
    $("#table1 td").each(function(){

        $(this).click(function(){
            $(".target").removeClass("tr_bg2");
            $(this).attr("class","target");
            $(this).addClass("tr_bg2");
        })
    });

    /*$("#table1 tr").each(function(){

        $(this).click(function(){
            $(".target").removeClass("tr_bg");
            $(this).attr("class","target");
            $(this).addClass("tr_bg");
        })

    });
*/
    $(".page_number li").click(function(){
        //$(this).addClass("table_li_click").siblings().removeClass("table_li_click")
        $(this).css("background","#8DC5FF").siblings().css("background","#ffffff")
        $(this).css("color","#333333").siblings().css("color","#333333")
    });

});

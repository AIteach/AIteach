
    // var option = myChart.getOption();
    // var categories=option.series[0].categories;
    // var legend=option.legend[0];
    // var nodesOption = option.series[0].nodes;
    // var linksOption = option.series[0].links;


 



$('#add').click(function (argument) {
    $('#lastwarming').html(" ");
    $('#newcategory').val("");
    $('#newnode').val("");
    $('#selectdiv').hide();
    $('#changediv').hide();
    $('#rootdiv').toggle(600);
    $('#newbtn').hide();
    $('#delbtn').hide();
    $('#changebtn').hide();
    $('#chosecate').html("<option>无</option>");
    var option = myChart.getOption();
    var categories=option.series[0].categories;
    for(var w=0;w<categories.length;w++){
        $('#chosecate').append("<option>"+categories[w].name+"</option>")
    }
    $('#addbtn3').unbind("click").bind("click",function(){
            // body...
        var option1 = myChart.getOption();
        var categories1=option1.series[0].categories;
        var linksOption1 = option1.series[0].links;
        var nodesOption1 = option1.series[0].nodes;
        var chosecate=$('#chosecate option:selected').text();
        var newnode=$('#newnode').val();
        if (newnode!=""&& chosecate!="无") {
            for(var i=0;i<categories1.length;i++){
                if (categories1[i].name==chosecate) {
                    nodesOption1.length++;
                    nodesOption1[nodesOption1.length-1]={
                        'id':nodesOption1.length-1,
                        'name':newnode,
                        'category': i,
                        'value':nodesOption1.length-1,
                        'symbolSize': 30
                    };
                }
            }
            myChart.setOption(option1);
        }
        if (newnode!=""&& chosecate!="无") {
            $('#newnode').val(""); 
        }
    });

    $('#addbtn4').unbind("click").bind("click",function(){
        var option2 = myChart.getOption();
        var categories2=option2.series[0].categories;
        var linksOption2 = option2.series[0].links;
        var nodesOption2 = option2.series[0].nodes;
        var legend=option2.legend[0];
        var newcategory=$('#newcategory').val();
        var flag=true;
        if (newcategory=="") {
            flag=false;
            return;
        }else{
            for(var c=0;c<categories2.length;c++){
                if (categories2[c].name==newcategory) {
                    $('#warming').html("已存在改目录！");
                    flag=false;
                    break;
                }
            }
        }
        if (flag) {
            categories2.length++;            
            legend.data.length++;
            legend.data[legend.data.length-1]=newcategory;
            categories2[categories2.length-1]={
                'name':newcategory
            };
            myChart.setOption(option2); 
            $('#chosecate').append("<option>"+categories2[categories2.length-1].name+"</option>");
        }
        if (flag) {
            $('#newcategory').val("");
        }
    });
    
});

function rootdivhide(argument) {
    // body...
    $('#rootdiv').css('display','none');
}
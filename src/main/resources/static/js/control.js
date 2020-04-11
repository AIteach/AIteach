 myChart = echarts.init(document.getElementById('main'));
    // var i=0;
    myChart.on("click", function (param){
        console.log(param.seriesName);  //legend的名称
          console.log(param.name);  //X轴的值
         console.log(param.value);
         console.log(param.type);
         console.log(param);
        $('#lastwarming').html(" ");
        $('#selectdiv').hide();
        $('#changediv').hide();
        $('#rootdiv').hide();
        $('#newbtn').toggle();
        $('#delbtn').toggle();
        $('#changebtn').toggle();
        var option = myChart.getOption();
        var legend=option.legend[0];
        var nodesOption = option.series[0].nodes;
        var linksOption = option.series[0].links;
        var categories=option.series[0].categories;    
        // i++;//标志函数执行次数 并在后面输出，方便调试
        //  console.log("执行click点击后的category如下");
        // console.log(categories);
        // console.log("执行click点击后的nodes如下");
        // console.log(nodesOption);
        // console.log("执行click点击后的link如下");
        //  console.log(linksOption);
         var fnMyFunc1;
        // 增
        $('#newbtn').unbind("click").bind("click",fnMyFunc1 = function(){
            //初始化
            $('#lastwarming').html(" ");
            $('#changediv').hide();
            $('#rootdiv').hide();
            $('#selectdiv').show(600);
            $('#categories').html("<option>无</option>");
            $('#newlinkwarming').html(" ");
            $('#linkvalue1').val("");
            $('#linkvalue2').val("");
            for(var w=0;w<categories.length;w++){
                $('#categories').append("<option>"+categories[w].name+"</option>")
            }
            $('#nodes').html("<option>无</option>");
            for(var k=0;k<nodesOption.length;k++){
                if (param.data.name!=nodesOption[k].name) {
                    $('#nodes').append("<option>"+nodesOption[k].name+"</option>");
                }
            }
            $('.currnode_').html('<b>当前节点为:</b>'+param.data.name);
            $('#categories_').html('<b>当前目录为:</b>'+categories[param.data.category].name);
            $('#newnodename').val("");

            $('#addbtn1').unbind("click").click(function () {
                console.log("添加关联结点");
                var newnodename=$('#newnodename').val();
                var linkvalue1=$('#linkvalue1').val();
                var categoriesval=$('#categories option:selected').text();
                if (categoriesval=="无"||newnodename==""||linkvalue1=="") {
                    $('#newnodenamewarming').html("上面的选项不能为空!");
                }else{
                    for(var i=0;i<categories.length;i++){
                        if (categories[i].name==categoriesval) {
                            nodesOption.length++;
                            nodesOption[nodesOption.length-1]={
                                'id':nodesOption.length-1,
                                'name':newnodename,
                                'category': i,
                                'value':2,
                                'symbolSize': 30
                            };
                        }
                    }
                   
                    linksOption.length++;
                    linksOption[linksOption.length-1]={
                        'value':linkvalue1,
                        'source':nodesOption[nodesOption.length-1].id,
                        'target':param.data.id 
                    };
                    
                    myChart.setOption(option);
                }
                if (newnodename!=""&&categoriesval!="无") {
                    $('#newnodenamewarming').html("");
                    $('#linkvalue1').val("");
                    $('#newnodename').val("");
                }
            });

            $('#addbtn2').unbind("click").click(function () {
                console.log("添加次级链接");
                var nodesval=$('#nodes option:selected').text();//关系节点名称
                var linkvalue2=$('#linkvalue2').val();
                if (nodesval!="无"&&linkvalue2!="") {
                    var flag=true;
                    for(var n=0;n<nodesOption.length;n++){
                        if (nodesval==nodesOption[n].name) {
                            for(var j=0;j<linksOption.length;j++) {
                                if (linksOption[j].source==nodesOption[n].id&&linksOption[j].target==param.data.id) {
                                    $('#newlinkwarming').html("该连接已存在");
                                    flag=false;
                                    return;
                                }
                            }
                            linksOption.length++;
                            linksOption[linksOption.length-1]={
                                'value':linkvalue2,
                                'source':nodesOption[n].id,
                                'target':param.data.id 
                            };
                        }
                    }
                    myChart.setOption(option);
                }else{
                    $('#newlinkwarming').html("上面选项不能为空!");
                }
                if (flag) {
                    $('#newlinkwarming').html(" "); 
                    $('#linkvalue2').val(""); 
                }
            });
        });

        //删
        $('#delbtn').unbind("click").bind("click",fnMyFunc1 =function () {
            if (param.data.id<1) {
                $('#lastwarming').html('头节点不可删!');
                return;
            }else{
                for(var i=param.data.id;i<nodesOption.length;i++){
                    if (nodesOption[i+1]==undefined) {
                        nodesOption.length--;
                    }else{
                       nodesOption[i]=nodesOption[i+1]; 
                       nodesOption[i].id=i;
                    }
                }
                for(var m=0;m<linksOption.length;m++){
                    if (linksOption[m].target==param.data.id) {
                        linksOption[m].target=null;                    
                    }

                }
            }
            
            
            myChart.setOption(option);
            
            $('#selectdiv').hide(500);
            $('#changediv').hide();
            $('#newbtn').hide();
            $('#delbtn').hide();
            $('#changebtn').hide();
        });

        // 改
        $('#changebtn').unbind("click").bind("click",fnMyFunc1 =function () {
            $('#lastwarming').html(" ");
            $('#rootdiv').hide();
            $('#selectdiv').hide();
            $('#changediv').show(200);
            //初始化
            $('.currnode').html('当前节点为:'+param.data.name);
            $('#currcategory').html('当前目录为:'+categories[param.data.category].name);
            $('#currlinks').html("当前连接有:");
            $('#linkvalue3').val("");
            $('#dellinks').html("<option>无</option>");
            $('#newlinks').html("<option>无</option>");
            $('#changename').val("");
            for(var ch=0;ch<linksOption.length;ch++){
                if (linksOption[ch].target==param.data.id) {
                    $('#currlinks').append("<i>"+nodesOption[linksOption[ch].source].name+"、</i>");
                    $('#dellinks').append("<option>"+nodesOption[linksOption[ch].source].name+"</option>");
                }
            }
            for(var i=0;i<nodesOption.length;i++){
                if (param.data.name!=nodesOption[i].name) {
                    $('#newlinks').append("<option>"+nodesOption[i].name+"</option>");
                }
            }
            $('#change-ensure1').unbind("click").bind("click",fnMyFunc1 =function () {
                var changename=$('#changename').val();
                var dellinks=$('#dellinks option:selected').text();
                //修改名字
                if (changename!="") {
                    nodesOption[param.data.id].name=changename;
                }
                //删除连接
                for(var j=0;j<linksOption.length;j++){
                    if (linksOption[j].target==param.data.id) {
                        if (nodesOption[linksOption[j].source].name==dellinks) {
                            linksOption[j].value="";
                            linksOption[j].target=null;
                        }
                    }
                }
                myChart.setOption(option);
                if (changename!=""||dellinks!="无") {
                    $('.currnode').html('当前节点为:'+nodesOption[param.data.id].name);
                    $('#changename').val("");
                    $('#currlinks').html("当前连接有:");
                    $('#dellinks').html("<option>无</option>");
                    for(var ch=0;ch<linksOption.length;ch++){
                    if (linksOption[ch].target==param.data.id) {
                        $('#currlinks').append("<i>"+nodesOption[linksOption[ch].source].name+"、</i>");
                        $('#dellinks').append("<option>"+nodesOption[linksOption[ch].source].name+"</option>");
                        }
                     }
                } 
            });
            $('#change-ensure2').unbind("click").bind("click",fnMyFunc1 =function (){
                var linkvalue3=$('#linkvalue3').val();
                var newlinks=$('#newlinks option:selected').text();
                if (newlinks!="无"&&linkvalue3!="") {
                    for(var k=0;k<nodesOption.length;k++){
                        if (nodesOption[k].name==newlinks) {
                            linksOption.length++;
                            linksOption[linksOption.length-1]={
                                'value':linkvalue3,
                                'source':nodesOption[k].id,
                                'target':param.data.id 
                            };
                        }
                    }
                }
               myChart.setOption(option);
                if (newlinks!="无") {
                    $('#linkvalue3').val("");
                }                
            });

        });
        
    });


// 操作框
    function selectdivhide(){
        // body...
        $('#selectdiv').fadeOut(1000);
    }
    $('#addnodebtn').click(function () {
        // body...
        $('#addnodebtn').css('background-color','#3399CC');
        $('#addlinksbtn').css('background-color','#545454');
        $('#newnodesdiv').css('display','block');
        $('#newlinkdiv').css('display','none');
        $('#ensure2').css('display','none');       
        $('#ensure1').css('display','block');
    });
    $('#addlinksbtn').click(function () {
        // body...
        $('#addlinksbtn').css('background-color','#3399CC');
        $('#addnodebtn').css('background-color','#545454');
        $('#newnodesdiv').css('display','none');
        $('#newlinkdiv').css('display','block');
        $('#ensure1').css('display','none');
        $('#ensure2').css('display','block');
    });
    $('#changenodebtn').click(function () {
        // body...
        $('#changenodebtn').css('background-color','#3399CC');
        $('#changelinksbtn').css('background-color','#545454');
        $('#chnodediv').css('display','block');
        $('#changebtndiv1').css('display','block');
        $('#chlinkdiv').css('display','none');
        $('#changebtndiv2').css('display','none');
    });
    $('#changelinksbtn').click(function () {
        // body...
        $('#changelinksbtn').css('background-color','#3399CC');
        $('#changenodebtn').css('background-color','#545454');
        $('#chnodediv').css('display','none');
        $('#changebtndiv1').css('display','none');
        $('#chlinkdiv').css('display','block');
        $('#changebtndiv2').css('display','block');
    });
    function changedivhide(argument) {
        // body...
        $('#changediv').css('display','none');
    }
    $('#newcategory').click(function () {
        // body...
        $('#warming').html(" ");
    });
    $('#newnodebtn').click(function () {
        // body...
        $('#newnodebtn').css('background-color','#3399CC');
        $('#newcategorybtn').css('background-color','#545454');
        $('#newcategorydiv').css('display','none');
        $('#ensure4').css('display','none');
        $('#newnodediv').css('display','block');
        $('#ensure3').css('display','block');
    });
    $('#newcategorybtn').click(function () {
        // body...
        $('#newcategorybtn').css('background-color','#3399CC');
        $('#newnodebtn').css('background-color','#545454');
        $('#newcategorydiv').css('display','block');
        $('#ensure4').css('display','block');
        $('#newnodediv').css('display','none');
        $('#ensure3').css('display','none');
    });
    function newlinkwarminghide() {
        // body...
        $('#newlinkwarming').html(" ");
    }
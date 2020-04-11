   if(${loginType}){
        alert("1");
        var msg=${msg};
        console.log(msg.nodes);
        console.log(msg.links);
        var myChart = echarts.init(document.getElementById('main'));
        myChart.showLoading();  
        //var webkitDep=msg;                        
        myChart.hideLoading();  
        
        option = {  
        		title: {
                    text: ''
                },
                tooltip: {},
                legend: {  
                    data: msg.categories//此处的数据必须和关系网类别中name相对应  
                },  
                series: [{  
                    type: 'graph',  
                    layout: 'force',  
                    animation: true,  
                    label: {  
                        normal: {  
                            show: true,  
                            position: 'right'  
                        }  
                    },  
                    edgeLabel: {
		                normal: {
		                    show: true,
		                    textStyle: {
		                        fontSize: 10
		                    },
		                    formatter: "{c}"
		                }
		            },
                    draggable: true,  
                    data:  msg.nodes,/*.map(function (node, idx) {  
                    node.id = idx;  
                    return node;  
                })*/
                    categories: msg.categories,  
                    force: {  
                        edgeLength: 105,//连线的长度  
                        repulsion: 100  //子节点之间的间距  
                    },  
                    edges: msg.links  
                }]  
            };  
            myChart.setOption(option);  
            myChart.on("click", function (param){               
                var option = myChart.getOption();
                var data = param.data; //判断节点的相关数据是否正确     
                console.log(data);
                if (data!= null && data!= undefined) {
                    if (data.url!= null && data.url!= undefined) {
                        //根据节点的扩展属性url打开新页面
                        console.log(data.url);
                        window.open(data.url);
                    }
                }
                // alert(param.dataIndex+':'+option.series[0].data[param.dataIndex].name);
              //  alert(option.series[0].data[0].name);
              //  console.log(param.data);
            });
             myChart.setOption(option); 
    }
    else { alert(${loginType});}
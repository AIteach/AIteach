    var myChart = echarts.init(document.getElementById('main'));
    myChart.showLoading();
 
    myChart.hideLoading();
    var option={
        title: {
                    text: ''
                },
                tooltip: {},
                animationDurationUpdate: 1500,
                animationEasingUpdate: 'quinticInOut',
                label: {
                    normal: {
                        show: true,
                        textStyle: {
                            fontSize: 12
                        },
                    }
                },
        legend:{
            data:['总结点']
        },  
        series: [{
            type: 'graph',  
            layout: 'force',  
            animation: false,
            label: {  
                normal: {  
                    show: true,  
                    position: 'right'  
                }  
            },
            tooltip: {}, 
            draggable: true, 
            edgeLabel: {
                normal: {
                    show: true,
                    color:'black',
                    textStyle: {
                        fontSize: 14
                    },
                    formatter: "{c}"
                }
            },
            lineStyle: {
                    normal: {
                        color: 'source',
                        curveness: 0.3,
                        width:1.5
                    }
            }, 
            nodes:[
                {
                    id: 0,
                    name: "创作的图谱",  
                    value: 0,  
                    category: 0,
                    symbolSize: 50
                }
            ],  
            categories:[
                {
                    name:'学校'
                }
            ],  
            force: {
                edgeLength: 150,//连线的长度  
                repulsion: 260  //子节点之间的间距  
            },  
            links: [//2018-5-1 16:54 删除了这句
                /*{
                    value:null,
                    source:null,
                    target:null
                }*/
            ]  
        }]  
    };  
    myChart.setOption(option);
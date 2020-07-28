function msg(message) {
    // 警告提示框
    layer.msg(message, {
        icon: 2
    })
}

var nodeid = $('#hidden').val(); // 当前页面节点信息
var login_sign = $('#login_sign').val(); // 是否登录标识
var className = $('#classhidden').val();
Vue.filter('dateFormat', function (data) {
    // 定义时间处理函数
    var dt = new Date(data);// 时间戳为10位需*1000，时间戳为13位的话不需乘1000
    Y = dt.getFullYear();
    M = (dt.getMonth() + 1).toString().padStart(2, '0');
    D = dt.getDate().toString().padStart(2, '0');
    h = dt.getHours().toString().padStart(2, '0');
    m = dt.getMinutes().toString().padStart(2, '0');
    return Y + '-' + M + '-' + D + ' ' + h + ':' + m;
})

var vm = new Vue({
    // 控制 right 整个区域
    el: '#schmsg_tab',
    data: {
        show: true,    // 整个右边区域显示
        show_color: [true, false, false],  // 基本信息显示,发贴评论显示，数据可视化显示
        allComments: [],   // 评论专用数据
        msg: [],         // 节点数据
        comment_click: {}, // 评论点选
        comment_data: {"comment_sign": null, "comment_id": null, "user_id": null, "comment_data": null},// 添加评论的标志和id
        comment_show: false,// 评论框显示/隐藏标志
        response_click: {},// 回复点赞
        login_sign: login_sign,  // 是否登录标识
        className: className,
        classList: []
    },
    methods: {// 方法
        setCommentData(comment_sign, comment_id, user_id) {
            if (login_sign == undefined)
                msg("请先登录！")
            else {
                this.comment_show = true;// 把评论框打开
                this.comment_data.comment_data = null;
                this.comment_data.comment_sign = comment_sign// 完善其他信息
                this.comment_data.user_id = user_id
                if (comment_sign == "1") {
                    this.comment_data.comment_id = {"0": nodeid}
                } else if (comment_sign == "2") {
                    this.comment_data.comment_id = comment_id
                }
                // console.log(this.comment_data)
            }
        },
        resetComment() {
            this.comment_data.comment_data = null;
        },
        cancelComment() {// 评论框取消按钮
            this.comment_show = false
            this.comment_data.comment_data = null;
        },
        sendComment() {// 评论框提交按钮
            if (this.comment_data.comment_data == null) msg("评论内容不能为空！")
            else if (login_sign == undefined) msg("请先登录")
            else {// 提交数据
                // console.log(this.comment_data)
                $.ajax({
                    type: "POST",
                    url: "/setCommentData",
                    data: {
                        comment_sign: vm._data.comment_data.comment_sign,
                        comment_id: vm._data.comment_data.comment_id[0],
                        user_id: vm._data.comment_data.user_id[0],
                        comment_data: vm._data.comment_data.comment_data
                    },
                    dataType: 'json',
                    success: function (data) {
                        // console.log("最后的："+data)
                        if (data.code == 0) {
                            msg("评论失败！")
                        } else {
                            getAllComments()
                            vm._data.comment_show = false
                            vm._data.comment_data.comment_data = null;
                        }
                    }, error: function (e) {
                        msg("评论失败！")
                    }
                })
            }
        },
        clickZan(num, click_sign, login_sign, id) {
            // 点赞动作,如果num为1主评论点选，num为2回复点赞
            if (login_sign == undefined) {
                msg("请先登录")
            } else {
                // 点赞数据ajax
                $.ajax({
                    type: "POST",
                    url: "/setClick",
                    data: {
                        num: num,
                        id: id[0],
                        sign: click_sign
                    },
                    dataType: 'json',
                    success: function (data) {
                        if (data.code == 0) {
                            msg("点赞失败！")
                        } else {
                            getAllComments()
                        }
                    }, error: function (e) {
                        msg("获取失败！")
                    }
                })
            }
        },
        submit(commentId) {
            $.ajax({
                type: "POST",
                url: "/comment",
                data: {commentId: commentId,},
                dataType: 'json',
                success: function (data) {
                    vm._data.msg = data
                    alter(vm._data.msg.nodes[2].name)
                }, error: function (e) {
                    msg("获取失败！")
                }
            })
        },
        changeShow() {
            if (this.show == true) {
                this.show = false
                $('.toggle-btn-right').toggleClass('btn-right-temp');
            } else
                this.show = true
        },
        show_div(data) {
            // 基本信息，发贴评论区，数据可视化切换
            if (data == 1) {
                this.show_color = [true, false, false]
            } else if (data == 2) {
                getAllComments()
                this.show_color = [false, true, false]
            } else {
                this.show_color = [false, false, true]
            }
        }
    },
    created(){
        // `this` 指向 vm 实例
        SearchClassFromNetWork(className)
        console.log('a is: ' + this.show)
    }
    // 开始加载评论数据
    // getAllComments
})

function SearchClassFromNetWork(name) {
    console.log("SearchClassFromNetWork执行")
    $.ajax({
        type: "GET",
        url: "/getClassFromSearch",
        data: {name: className,},
        dataType: 'json',
        success: function (data) { // 把数据交给vm渲染
            vm._data.classList = data
            console.log(data)
        }, error: function (e) {
            msg("获取失败！")
        }
    });

}

function alter(id) {
    $.ajax({
        type: "POST",
        url: "/comment/getClassName",
        data: {nodeid: nodeid,},
        dataType: 'json',
        success: function (data) { // 把数据交给vm渲染
            vm._data.msg.nodes[2].name = data
            updata(vm._data.msg)
        }, error: function (e) {
            msg("获取失败！")
        }
    });
}

function getLikeClick(commentId, responId, vm_data) {
    // 根据取到的评论数据，添加用户是否对当前评论点赞数据
    // console.log(commentId)
    // console.log(responId)

    $.ajax({
        type: "POST",
        url: "/comment/LikeClick",
        data: {
            commentId: commentId,
            responId: responId
        },
        dataType: 'json',
        success: function (data) {
            // 根据code状态判断用户是否登录
            // 0为未登录，1为已经登录
            if (data.code == 0) {
                // 未登录，点击图标全部为false
                for (var i in vm_data) {
                    vm_data[i]["comment"]["userClick"] = false
                }
            } else {
                // 将返回的数组交给vm
                vm._data.comment_click = data['body']['commentClick']
                vm._data.response_click = data['body']['responseClick']
            }
            // 把数据交给vm渲染，最后显示前端评论数据
            vm._data.allComments = vm_data
        }, error: function (e) {
            msg("获取失败！")
        }
    });

}

function getAllComments() {
    // 获取当前节点的评论数据
    $.ajax({
            type: "POST",
            url: "/GetComment",
            data: {nodeid: nodeid,},
            dataType: 'json',
            success: function (data) {
                if (data.code == 1 && data.body != "") {
                    console.log(data.body)
                    var newdata = data.body
                    var commentId = [] // 获取所有主评论数据的id
                    var resopnId = []
                    for (var i in newdata) {
                        commentId.push(newdata[i].comment.id)
                        for (var j in newdata[i]['commentresponse']) {
                            resopnId.push(newdata[i]['commentresponse'][j]['commentResponse']['id'])
                        }
                    }
                    if (commentId != false) {
                        getLikeClick(commentId, resopnId, newdata) // 如果数据不为空，对数据进行补充
                    }
                } else {
                    msg("获取失败！")
                }
            }, error: function (e) {
                msg("获取失败！")
            }
        }
    );
}


function updata(msg) {
    // 画图 （copy）
    // console.log(msg);
    var myChart = echarts.init(document.getElementById('main'));
    myChart.showLoading();
    // var webkitDep=msg;
    myChart.hideLoading();

    option = {
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
                    fontSize: 20
                },
            }
        },
        legend: {
            data: msg.categories
            // 此处的数据必须和关系网类别中name相对应
        },
        series: [{
            type: 'graph',
            layout: 'force',
            animation: true,
            label: {
                normal: {
                    show: true,
                    position: 'right',
                    fontSize: 20
                }
            },
            edgeLabel: {
                normal: {
                    show: true,
                    textStyle: {
                        fontSize: 20
                    },
                    formatter: "{c}"
                }
            },
            draggable: true,
            data: msg.nodes,
            categories: msg.categories,
            force: {
                edgeLength: 250,// 连线的长度
                repulsion: 500
                // 子节点之间的间距
            },
            edges: msg.links
        }]
    };
    myChart.setOption(option);
    myChart.on("click", function (param) {
        var option = myChart.getOption();
        var data = param.data; // 判断节点的相关数据是否正确
        // console.log(data);
        if (data != null && data != undefined) {
            if (data.url != null && data.url != undefined) {
                window.location.href = data.url;
            }
        }
        // alert(param.dataIndex+':'+option.series[0].data[param.dataIndex].name);
        // alert(option.series[0].data[0].name);
        // console.log(param.data);
    });
    myChart.setOption(option);
}



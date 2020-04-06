<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>教育科技学学科数据中心操作文档</title>
	<style type="text/css">
		h3,p{
			text-align: left;
			font-size: 18px;
		}
	</style>
</head>
<body>
<div style="margin: 0 auto;width: 90%;overflow:auto;height: auto;text-align: center;">
	<h1>学科数据中心操作文档</h1>
	<h3>1.单节点添加和数据目录创建</h3>
	<img src="img/operation/0.png" width="400px">
	<p>点击新建按钮，在操作框出现后按照提示进行节点信息和目录信息的填写，注意每一项的信息填写都不能为空。</p>
	<img src="img/operation/1.png" width="400px">
	<p>新建“学校”目录和“佛山科学技术学院”节点</p>
	<img src="img/operation/2.png" width="400px">
	<h3>2.单节点的添加操作</h3>
	<p>在新建好“学校”目录和“佛山科学技术学院”节点后点击“佛山科学技术学院”节点</p>
	<img src="img/operation/3.png" width="400px">
	<p>此时出现三个操作按钮，点击添加按钮</p>
	<img src="img/operation/4.png" width="400px">
	<p>在新建一个“课程”目录和“php”节点后 ，在添加关联节点一栏里依次选择“课程”目录和填写节点名称“Java”和连接值“课程一”</p>
	<img src="img/operation/5.png" width="400px">
	<p>此时就成功为“佛山科学技术学院”节点添加了一个关联节点“java”，也就是次级的连接。如果还想将“php”节点添加到“佛山科学技术学院”节点的关联下，点击添加次级连接，依次填写信息</p>
	<img src="img/operation/6.png" width="400px">
	<p>或是“java”与“php”相连</p>
	<img src="img/operation/7.png" width="400px">
	<h3>3.单节点的信息修改</h3>
	<p>当我们新建完成前面的数据后，发现节点的名称或是连接有误，选中想修改的节点后点击改按钮，根据提示，在修改节点一栏里可以单独修改或是一同修改节点名称和删除不想要的连接</p>
	<img src="img/operation/8.png" width="400px">
	<p>在新增连接一栏下，添加新的连接，如先删除“java”与“php”的连接，再重新添加这两者的连接，这便相当于修改了两者的连接值</p>
	<img src="img/operation/9.png" width="400px">
	<h3>4.节点的删除</h3>
	<p>点击选中的节点，在点击删除当前节点按钮后，节点将会被清除，并且清除掉于该节点有关的连接，如删除“java”节点</p>
	<img src="img/operation/10.png" width="400px">
	<p>需要注意的是头节点不可删除，如果选中的是头节点，删除时将会报错</p>
	<img src="img/operation/11.png" width="400px">
	<h3>5.整个目录下的节点的隐藏和显示</h3>
	<p>系统默认是显示所以目录的全部节点，在创建目录后，我们会发现有顶部多出了几个目录icon和相应的名称，此时我们点击“学校”左边的icon，便会发现icon颜色变暗，“学校“目录下的全部节点都消失了，再次点击后又会显示出来</p>
	<p>icon颜色变暗，“学校“目录下的全部节点都消失</p>
	<img src="img/operation/12.png" width="400px">
	<p>再次点击icon，颜色恢复，“学校“目录下的全部节点都显示</p>
	<img src="img/operation/13.png" width="400px">

</div>
</body>
</html>
</html>
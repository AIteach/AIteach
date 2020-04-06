package com.example.demo.system.mysql.entity;

public class CommentResponseAndName {
	//commentresponse多表查询接收的实体类，加入用户名
		private CommentResponse commentResponse;
		private String username; // 此评论所属id的用户名
		private String rusername; //被评论所属id的用户名
		public CommentResponseAndName(){
			
		}
		public CommentResponseAndName(CommentResponse commentResponse, String username,String rusername) {
			this.commentResponse = commentResponse;
			this.username = username;
			this.rusername=rusername;
		}
		public CommentResponse getCommentResponse() {
			return commentResponse;
		}
		public void setCommentResponse(CommentResponse commentResponse) {
			this.commentResponse = commentResponse;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getRusername() {
			return rusername;
		}
		public void setRusername(String rusername) {
			this.rusername = rusername;
		}
		@Override
		public String toString() {
			return "CommentResponseAndName [commentResponse=" + commentResponse + ", username=" + username
					+ ", rusername=" + rusername + "]";
		}
		


}

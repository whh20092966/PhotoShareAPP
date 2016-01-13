package com.yuan.cfycomment.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yuan.cfycomment.R;
import com.yuan.cfycomment.view.CommentListView;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PostAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private ViewHolder viewHolder;
	private List<HashMap<String, Object>> list_comment; // 一级评论数据
	private List<List<HashMap<String, Object>>> list_comment_child; // 二级评论数据
	private Context context;
	private OnClickListener myOnitemcListener;
	private CommentAdapter myAdapter;

	public PostAdapter(Context context,
			List<HashMap<String, Object>> list_comment,
			List<List<HashMap<String, Object>>> list_comment_child,
			OnClickListener myOnitemcListener, CommentAdapter myAdapter) {
		this.context = context;
		this.inflater = LayoutInflater.from(context);
		this.myAdapter = myAdapter;
		this.list_comment = new ArrayList<HashMap<String, Object>>();
		this.list_comment_child = new ArrayList<List<HashMap<String, Object>>>();
		this.myOnitemcListener = myOnitemcListener;
		this.list_comment.addAll(list_comment);
		this.list_comment_child.addAll(list_comment_child);
	}

	public void clearList() {
		this.list_comment.clear();
		this.list_comment_child.clear();
	}

	public void updateList(List<HashMap<String, Object>> list_comment,
			List<List<HashMap<String, Object>>> list_comment_child) {
		this.list_comment.addAll(list_comment);
		this.list_comment_child.addAll(list_comment_child);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list_comment.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list_comment.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_post, null);
			viewHolder = new ViewHolder();
			viewHolder.iv_user_photo = (ImageView) convertView
					.findViewById(R.id.iv_user_photo);
			viewHolder.tv_user_name = (TextView) convertView
					.findViewById(R.id.tv_user_name);
			viewHolder.tv_user_comment = (TextView) convertView
					.findViewById(R.id.tv_user_comment);

			viewHolder.tv_user_reply = (Button) convertView
					.findViewById(R.id.tv_user_reply);
			viewHolder.lv_user_comment_replys = (CommentListView) convertView
					.findViewById(R.id.lv_user_comment_replys);
			viewHolder.tv_user_reply.setTag(position);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.tv_user_name.setText("david");
		viewHolder.tv_user_comment.setText(list_comment.get(position)
				.get("content").toString());
		viewHolder.lv_user_comment_replys.setSelector(new ColorDrawable(
				Color.TRANSPARENT));
		// if (list_comment_child.size() > position)
		if (list_comment_child.get(position) != null)
			viewHolder.lv_user_comment_replys
					.setAdapter(new CommentAdapter(context,
							list_comment_child.get(position)));

		viewHolder.tv_user_reply.setTag(position);
		viewHolder.tv_user_reply.setOnClickListener(myOnitemcListener);
		return convertView;
	}

	public class ViewHolder {
		private ImageView iv_user_photo; // 品论者 头像
		private TextView tv_user_name; // 品论者 昵称
		private TextView tv_user_comment; // 品论者 一级品论内容
		private Button tv_user_reply; // 品论者 二级品论内容
		private CommentListView lv_user_comment_replys; // 品论者 二级品论内容列表

	}
}

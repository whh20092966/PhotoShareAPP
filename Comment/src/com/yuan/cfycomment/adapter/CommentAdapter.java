package com.yuan.cfycomment.adapter;

import java.util.HashMap;
import java.util.List;
import com.yuan.cfycomment.R;
import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CommentAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<HashMap<String, Object>> list; // 二级评论数据
	private ViewHolder viewHolder;
	//private static final String TAG = "TakePhoto - CommentAdapter";
	public CommentAdapter(Context context,
			List<HashMap<String, Object>> list) {
		inflater = LayoutInflater.from(context);
		// this.list = new ArrayList<HashMap<String, Object>>();
		// this.list.addAll(list);

		
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	public void clearList() {
		this.list.clear();
	}

	public void updateList(List<HashMap<String, Object>> list_comment) {
		this.list.addAll(list_comment);
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
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
			convertView = inflater.inflate(R.layout.item_comment, null);
			viewHolder = new ViewHolder();
			viewHolder.tv_comment_text = (TextView) convertView
					.findViewById(R.id.tv_comment);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		String userName = "王尼玛";
		String reply = "回复";
		String atName = "爆走王尼妹爆走王尼妹";
		String replayContent = list.get(position).get("content").toString();

		//setCommentWithAtUser(viewHolder, userName, reply, atName, replayContent);
		setCommnt(viewHolder, userName, replayContent);
		return convertView;
	}
	
	private void setCommentWithAtUser(ViewHolder holder, String userName, String reply, String atName, String replayContent){
		String content = userName + reply + atName +  ": " + replayContent;
		
		int userNameLastIndex = userName.length();
		int replyEndIndex = userNameLastIndex + reply.length();
		int atNameEndIndex = replyEndIndex + atName.length();

		SpannableString ss = new SpannableString(content);
        ss.setSpan(new ForegroundColorSpan(Color.BLUE), 0, userNameLastIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new ForegroundColorSpan(Color.BLACK), userNameLastIndex, replyEndIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new ForegroundColorSpan(Color.BLUE),replyEndIndex, atNameEndIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        viewHolder.tv_comment_text.setText(ss);
        viewHolder.tv_comment_text.setMovementMethod(LinkMovementMethod.getInstance());
	}
	
	private void setCommnt(ViewHolder holder, String userName, String replayContent){
		String content = userName + ": " + replayContent;
		
		int userNameLastIndex = userName.length();
		/*int replyEndIndex = userNameLastIndex + reply.length();
		int atNameEndIndex = replyEndIndex + atName.length();*/

		SpannableString ss = new SpannableString(content);
        ss.setSpan(new ForegroundColorSpan(Color.BLUE), 0, userNameLastIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        /*ss.setSpan(new ForegroundColorSpan(Color.BLACK), userNameLastIndex, replyEndIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new ForegroundColorSpan(Color.BLUE),replyEndIndex, atNameEndIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);*/

        viewHolder.tv_comment_text.setText(ss);
        viewHolder.tv_comment_text.setMovementMethod(LinkMovementMethod.getInstance());
	}

	public class ViewHolder {
		private TextView tv_comment_text; // 评论 内容
	}
}

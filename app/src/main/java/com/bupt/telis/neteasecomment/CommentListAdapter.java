package com.bupt.telis.neteasecomment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Telis on 2015/6/9.
 */
public class CommentListAdapter extends BaseAdapter {
    List<Comment> list;
    Context context;

    public CommentListAdapter(Context context, List<Comment> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        final ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.comment, null);
            viewHolder = new ViewHolder();
            viewHolder.id = (TextView) view.findViewById(R.id.id);
            viewHolder.locale = (TextView) view.findViewById(R.id.locale);
            viewHolder.time = (TextView) view.findViewById(R.id.time);
            viewHolder.vote = (TextView) view.findViewById(R.id.vote);
            viewHolder.comment = (TextView) view.findViewById(R.id.comment);
            viewHolder.briefComment = (ExtraView) view.findViewById(R.id.extra_view);
            viewHolder.voteImage = (ImageView) view.findViewById(R.id.vote_image);
            //            viewHolder.viewStub = (ViewStub) view.findViewById(R.id.view_stub);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) convertView.getTag();
        }
        initView(position, viewHolder);
        viewHolder.voteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageView = (ImageView) v;
                imageView.setImageResource(R.drawable.icon);
                int count = Integer.valueOf(viewHolder.vote.getText().toString());
                count++;
                viewHolder.vote.setText(String.valueOf(count));
            }
        });
        return view;
    }

    private void initView(int position, ViewHolder viewHolder) {
        Comment comment = list.get(position);
        viewHolder.id.setText(comment.getAuthor().getName());
        viewHolder.locale.setText(comment.getAuthor().getLocale());
        viewHolder.time.setText(comment.getCreateTime().toString());
        viewHolder.vote.setText(String.valueOf(comment.getVote()));
        viewHolder.comment.setText(comment.getContent());
        if (comment.getBriefComments().size() > 0) {
            viewHolder.briefComment.setVisibility(View.VISIBLE);
            viewHolder.briefComment.setComments(comment.getBriefComments());
        }
    }

    private class ViewHolder {
        TextView id;
        TextView locale;
        TextView time;
        TextView vote;
        TextView comment;
        ExtraView briefComment;
        ImageView voteImage;
    }


}

package com.bupt.telis.neteasecomment;

import android.animation.*;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * 评论列表的适配器，重要类
 * Created by Telis on 2015/6/9.
 */
public class CommentListAdapter extends BaseAdapter {
    List<Comment> list;
    Context context;
    ViewHolder viewHolder;

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
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.comment, null);
            viewHolder = new ViewHolder();
            viewHolder.id = (TextView) view.findViewById(R.id.id);
            viewHolder.locale = (TextView) view.findViewById(R.id.locale);
            viewHolder.time = (TextView) view.findViewById(R.id.time);
            viewHolder.vote = (TextView) view.findViewById(R.id.vote);
            viewHolder.comment = (TextView) view.findViewById(R.id.comment);
            viewHolder.voteUp = (TextView) view.findViewById(R.id.vote_up);
            viewHolder.briefComment = (ExtraView) view.findViewById(R.id.extra_view);
            viewHolder.voteImage = (ImageView) view.findViewById(R.id.vote_image);
            viewHolder.icon = (CircleImageView) view.findViewById(R.id.icon);
            viewHolder.viewStub = (ViewStub) view.findViewById(R.id.view_stub);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) convertView.getTag();
        }
        initView(position, viewHolder);
        viewHolder.voteImage.setOnClickListener(new View.OnClickListener() {
            private boolean isClicked = false;

            @Override
            public void onClick(View v) {
                ImageView imageView = (ImageView) v;
                int count;
                if (!isClicked) {
                    animPlay();
                    imageView.setImageResource(R.drawable.voted);
                    count = Integer.valueOf(viewHolder.vote.getText().toString());
                    count++;
                    isClicked = true;
                } else {
                    imageView.setImageResource(R.drawable.vote);
                    count = Integer.valueOf(viewHolder.vote.getText().toString());
                    count--;
                    isClicked = false;
                }
                viewHolder.vote.setText(String.valueOf(count));
            }

            /**
             * +1 的动画
             */
            private void animPlay() {
                viewHolder.voteUp.setVisibility(View.VISIBLE);
                ObjectAnimator up = ObjectAnimator.ofFloat(viewHolder.voteUp, "translationY",
                        -40f);
                ObjectAnimator fade = ObjectAnimator.ofFloat(viewHolder.voteUp, "alpha",
                        0f);
                AnimatorSet set = new AnimatorSet();
                set.play(up).with(fade);
                set.start();
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        viewHolder.voteUp.setTranslationY(0f);
                        viewHolder.voteUp.setAlpha(1);
                        viewHolder.voteUp.setVisibility(View.INVISIBLE);
                    }
                });
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
        viewHolder.icon.setBackgroundResId(comment.getAuthor().getIcon());
        if (comment.getBriefComments().size() > 0) {
            viewHolder.briefComment.setVisibility(View.VISIBLE);
            viewHolder.briefComment.setComments(comment.getBriefComments());
        }
        if (position == 0) {
            viewHolder.viewStub.setVisibility(View.VISIBLE);
        } else {
            viewHolder.viewStub.setVisibility(View.GONE);
        }
    }

    private class ViewHolder {
        /**
         * id
         */
        TextView id;
        /**
         * location
         */
        TextView locale;
        /**
         * post time
         */
        TextView time;
        /**
         * vote number
         */
        TextView vote;
        ImageView voteImage;
        /**
         * +1 TextView
         */
        TextView voteUp;
        /**
         * comment
         */
        TextView comment;
        /**
         * reply comments
         */
        ExtraView briefComment;
        /**
         * icon
         */
        CircleImageView icon;
        /**
         * hot comment pin
         */
        ViewStub viewStub;
    }


}

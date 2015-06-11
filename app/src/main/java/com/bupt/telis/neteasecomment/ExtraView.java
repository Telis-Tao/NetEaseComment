package com.bupt.telis.neteasecomment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Telis on 2015/6/9.
 */
public class ExtraView extends LinearLayout {
    /**
     * frame distance
     */
    public static final int FRAME_MARGIN = 5;

    /**
     * max frame number
     */
    public static final int MAX_FRAME_NUMBER = 5;
    private Drawable drawable;
    private Context context;
    private List<BriefComment> comments;
    //    private View view;
    //    private TextView idTextView;
    //    private TextView countTextView;
    //    private TextView contentTextView;

    public ExtraView(Context context) {
        this(context, null);

    }

    public ExtraView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setOrientation(VERTICAL);
        drawable = getResources().getDrawable(R.drawable.biankuang);
    }

    public void setComments(List<BriefComment> comments) {
        //remove this method induces a bug.
        this.comments = comments;
        removeAllViews();
        int size = comments.size();
        if (size > 3) {
            addView(getView(comments.get(0), 0, 0, 3));
            addView(getMoreView(1, 3));
            addView(getView(comments.get(size - 1), size - 1, 2, 3));
        } else {
            for (int i = 0; i < size; i++) {
                addView(getView(comments.get(i), i, i, size));
            }
        }
    }

    public void showAllView() {
        removeAllViews();
        int size = comments.size();
        for (int i = 0; i < size; i++) {
            addView(getView(comments.get(i), i, i, size));
        }
    }

    private View view;
    private LayoutParams params;

    private View getMoreView(int index, int size) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.more_comments, null);
        }
        params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        int margin = (size - index) * FRAME_MARGIN;
        params.setMargins(margin, 0, margin, 0);
        view.setLayoutParams(params);
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showAllView();
            }
        });
        return view;
    }

    /**
     * @param comment 评论
     * @param index   楼层数字-1
     * @param count   在View 中处于第几个
     * @param size    View 中显示楼层的总数
     * @return return a View
     */
    private View getView(BriefComment comment, int index, int count, int size) {
        View view = LayoutInflater.from(context).inflate(R.layout.extra_comment, null);
        TextView idTextView = (TextView) view.findViewById(R.id.brief_id);
        TextView countTextView = (TextView) view.findViewById(R.id.brief_count);
        TextView contentTextView = (TextView) view.findViewById(R.id.brief_content);
        idTextView.setText(comment.getName());
        countTextView.setText(String.valueOf(index + 1));
        contentTextView.setText(comment.getContent());
        params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        int margin;
        if (count > MAX_FRAME_NUMBER) {
            margin = (size - MAX_FRAME_NUMBER) * FRAME_MARGIN;
        } else {
            margin = (size - count) * FRAME_MARGIN;
        }
        params.setMargins(margin, 0, margin, 0);
        view.setLayoutParams(params);
        return view;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        /*
        在FloorView绘制子控件前先绘制层叠的背景图片
         */
        int top;
        for (int i = getChildCount() - 1; i >= 0; i--) {
            View view = getChildAt(i);
            if (getChildCount() > MAX_FRAME_NUMBER) {
                if (i > MAX_FRAME_NUMBER) {
                    top = getChildAt(0).getTop() + MAX_FRAME_NUMBER * FRAME_MARGIN;
                } else {
                    top = getChildAt(0).getTop() + (MAX_FRAME_NUMBER - 1 - i) * FRAME_MARGIN;
                }
            } else {
                top = getChildAt(0).getTop() + (getChildCount() - i) * FRAME_MARGIN;
            }
            drawable.setBounds(view.getLeft(), top, view.getRight(), view
                    .getBottom());
            drawable.draw(canvas);
        }
        super.dispatchDraw(canvas);
    }
}

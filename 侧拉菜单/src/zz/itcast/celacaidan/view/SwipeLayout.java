package zz.itcast.celacaidan.view;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewDebug.RecyclerTraceType;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class SwipeLayout extends FrameLayout {

	private ViewDragHelper mdragHelper;

	public SwipeLayout(Context context) {
		super(context);
		mdragHelper = ViewDragHelper.create(this, 1.0f, callbback);
		System.out.println("DFSDF");
	}

	public SwipeLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		mdragHelper = ViewDragHelper.create(this, 1.0f, callbback);
		System.out.println("DSFS");
	}

	public SwipeLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mdragHelper = ViewDragHelper.create(this, 1.0f, callbback);
		System.out.println("JKJKLJ");
	}
	// 2.将事件中断传递给 mdragHelper
	public boolean onInterceptTouchEvent(android.view.MotionEvent ev) {
		// 确定是否拦截
		return mdragHelper.shouldInterceptTouchEvent(ev);
		//若拦截直接返回true就可以了；
	};
	public boolean onTouchEvent(android.view.MotionEvent event) {
		try {
			// 将触摸事件传递给mdraghelper；解析touch事件；
			mdragHelper.processTouchEvent(event);
						
		} catch (Exception e) {

		}
		// 为了使move和up事件全部都过来
		return true;
	};
	// 当xml填充为对象的时候调用
	protected void onFinishInflate() {
		
		super.onFinishInflate();
		if (getChildCount() < 2) {
			throw new IllegalArgumentException(
					"you must have 2 chlidern at least！！");
		}
		if (!(getChildAt(0) instanceof ViewGroup)
				|| !(getChildAt(1) instanceof ViewGroup)) {
			throw new IllegalStateException(
					"you child  must have to  viewGroup！！");
		}
		mBacklayout = (ViewGroup) getChildAt(0);
		mfrontlayout = (ViewGroup) getChildAt(1);
	};
	// 抽取匿名内部类
	Callback callbback = new Callback() {
		// 决定自子view可以滑动
		@Override
		public boolean tryCaptureView(View child, int pointerId) {
			// TODO Auto-generated method stub
			// 如果是前面的就能移动，不是前面的则不能移动；
			return child == mfrontlayout;
			//全部移动返回true
			//return true;
		}
		// 1.正在移动的位置 2.建议达到的位置 3.横向的瞬间变化量
		public int clampViewPositionHorizontal(View child, int left, int dx) {
			System.out.println("m" + mBacklayout.getLeft() + "dx" + dx);

			return left;
		};
		// 当view启动的时候调用
		public void onViewCaptured(View capturedChild, int activePointerId) {

		};
		// 计算动画执行的时间；
		// 大于0就可以了
		public int getViewHorizontalDragRange(View child) {

			return mrange;
		};

		// 实际拖动的时候调用 left 建议达到的位置 dx 顺东的位置；
         @Override
		public void onViewPositionChanged(View changedView, int left, int top,
				int dx, int dy) {
        
        	 if (mfrontlayout==changedView) { 
        		 System.out.println("jjj");
        		 mBacklayout.offsetLeftAndRight(dx);
    		 }
		};

	/*
	 *   //如果返回 true 子view的上下可以移动。 
		public int clampViewPositionVertical(View child, int top, int dy) {

			return top;
		};
*/
	};
	private ViewGroup mBacklayout;
	private ViewGroup mfrontlayout;
	private int mWidth;
	private int mHight;
	private int mrange;

	// 测量宽和高；
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	// 放置子view 
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		// TODO Auto-generated method stub
		super.onLayout(changed, left, top, right, bottom);

		// TODO
		// 把背景图片防盗相对的父元素的
		// 三定已经是可以确定子view的位置了。为什么还有第四个参数。
		//子view的形状没有确实。三参数没有办法确定。
		mBacklayout.layout(mWidth, 0, mrange + mWidth, mHight);
		// mfrontlayout.layout(l, t, r, b);
	}
	// 测量完成的时候调用这个方法
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		// 测量是本身的宽度
		mWidth = w;
		// 测量的是本身过的高
		mHight = h;
		// 这是背景图片的高度
		mrange = mBacklayout.getMeasuredWidth();
		            //TODO 源码里没有怎么可以调用？
		          mBacklayout.getWidth();
		  System.out.println("mBacklayout.getMeasuredWidth()"+mrange);
		  System.out.println("  mBacklayout.getWidth()"+mBacklayout.getWidth());
		  //RecyclerTraceType
	}
}

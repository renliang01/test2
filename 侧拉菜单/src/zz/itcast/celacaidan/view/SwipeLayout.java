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
	// 2.���¼��жϴ��ݸ� mdragHelper
	public boolean onInterceptTouchEvent(android.view.MotionEvent ev) {
		// ȷ���Ƿ�����
		return mdragHelper.shouldInterceptTouchEvent(ev);
		//������ֱ�ӷ���true�Ϳ����ˣ�
	};
	public boolean onTouchEvent(android.view.MotionEvent event) {
		try {
			// �������¼����ݸ�mdraghelper������touch�¼���
			mdragHelper.processTouchEvent(event);
						
		} catch (Exception e) {

		}
		// Ϊ��ʹmove��up�¼�ȫ��������
		return true;
	};
	// ��xml���Ϊ�����ʱ�����
	protected void onFinishInflate() {
		
		super.onFinishInflate();
		if (getChildCount() < 2) {
			throw new IllegalArgumentException(
					"you must have 2 chlidern at least����");
		}
		if (!(getChildAt(0) instanceof ViewGroup)
				|| !(getChildAt(1) instanceof ViewGroup)) {
			throw new IllegalStateException(
					"you child  must have to  viewGroup����");
		}
		mBacklayout = (ViewGroup) getChildAt(0);
		mfrontlayout = (ViewGroup) getChildAt(1);
	};
	// ��ȡ�����ڲ���
	Callback callbback = new Callback() {
		// ��������view���Ի���
		@Override
		public boolean tryCaptureView(View child, int pointerId) {
			// TODO Auto-generated method stub
			// �����ǰ��ľ����ƶ�������ǰ��������ƶ���
			return child == mfrontlayout;
			//ȫ���ƶ�����true
			//return true;
		}
		// 1.�����ƶ���λ�� 2.����ﵽ��λ�� 3.�����˲��仯��
		public int clampViewPositionHorizontal(View child, int left, int dx) {
			System.out.println("m" + mBacklayout.getLeft() + "dx" + dx);

			return left;
		};
		// ��view������ʱ�����
		public void onViewCaptured(View capturedChild, int activePointerId) {

		};
		// ���㶯��ִ�е�ʱ�䣻
		// ����0�Ϳ�����
		public int getViewHorizontalDragRange(View child) {

			return mrange;
		};

		// ʵ���϶���ʱ����� left ����ﵽ��λ�� dx ˳����λ�ã�
         @Override
		public void onViewPositionChanged(View changedView, int left, int top,
				int dx, int dy) {
        
        	 if (mfrontlayout==changedView) { 
        		 System.out.println("jjj");
        		 mBacklayout.offsetLeftAndRight(dx);
    		 }
		};

	/*
	 *   //������� true ��view�����¿����ƶ��� 
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

	// ������͸ߣ�
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	// ������view 
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		// TODO Auto-generated method stub
		super.onLayout(changed, left, top, right, bottom);

		// TODO
		// �ѱ���ͼƬ������Եĸ�Ԫ�ص�
		// �����Ѿ��ǿ���ȷ����view��λ���ˡ�Ϊʲô���е��ĸ�������
		//��view����״û��ȷʵ��������û�а취ȷ����
		mBacklayout.layout(mWidth, 0, mrange + mWidth, mHight);
		// mfrontlayout.layout(l, t, r, b);
	}
	// ������ɵ�ʱ������������
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		// �����Ǳ���Ŀ��
		mWidth = w;
		// �������Ǳ�����ĸ�
		mHight = h;
		// ���Ǳ���ͼƬ�ĸ߶�
		mrange = mBacklayout.getMeasuredWidth();
		            //TODO Դ����û����ô���Ե��ã�
		          mBacklayout.getWidth();
		  System.out.println("mBacklayout.getMeasuredWidth()"+mrange);
		  System.out.println("  mBacklayout.getWidth()"+mBacklayout.getWidth());
		  //RecyclerTraceType
	}
}

package com.xiemiao.myapplication.base;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.jess.arms.base.delegate.IActivity;
import com.jess.arms.integration.lifecycle.ActivityLifecycleable;
import com.jess.arms.mvp.IPresenter;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.xiemiao.myapplication.R;
import com.xiemiao.myapplication.common.mvp.ui.activity.LoginActivity;
import com.xiemiao.myapplication.common.mvp.ui.activity.MainActivity;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;
import pub.devrel.easypermissions.EasyPermissions;
import timber.log.Timber;

import static com.jess.arms.utils.ThirdViewUtil.convertAutoView;

/**
 * 因为java只能单继承,所以如果有需要继承特定Activity的三方库,那你就需要自己自定义Activity
 * 继承于这个特定的Activity,然后按照将BaseActivity的格式,复制过去记住一定要实现{@link IActivity}
 */
public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity implements IActivity, ActivityLifecycleable, View.OnClickListener, EasyPermissions.PermissionCallbacks {
    protected final String TAG = this.getClass().getSimpleName();
    private Unbinder mUnbinder;
    private final BehaviorSubject<ActivityEvent> mLifecycleSubject = BehaviorSubject.create();
    @Inject
    protected P mPresenter;

    /*================初始化通用标题相关=====start============*/
    private Context cContext;
    private Toast toast;
    private Dialog progressDialog;
    /**
     * 标题布局
     */
    private RelativeLayout mTopLayout;
    /**
     * 返回按钮
     */
    private Button mBack;
    /**
     * 标题文字
     */
    private TextView mTitleText;
    /**
     * 右边文字按钮
     */
    protected TextView mTitleRightText;
    private TextView mTitleLeftText;
    private ImageView mTitleLeftImage;
    private ImageView mTitleRightImage;
    public LinearLayout mTopContainerLayout;
    private TextView mTitleSmall;
    /*================初始化通用标题相关=====end============*/


    @NonNull
    @Override
    public final Subject<ActivityEvent> provideLifecycleSubject() {
        return mLifecycleSubject;
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = convertAutoView(name, context, attrs);
        return view == null ? super.onCreateView(name, context, attrs) : view;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            cContext = this;
            int layoutResID = initView(savedInstanceState);
            if (layoutResID != 0) {//如果initView返回0,框架则不会调用setContentView(),当然也不会 Bind ButterKnife
                setContentView(layoutResID);
                //绑定到butterknife
                mUnbinder = ButterKnife.bind(this);

                //初始化常规标题布局
                initCommonTitleView();

                //在创建的时候初始化沉浸式状态栏
                initImmersionBar();

                //申请获取6.0运行时权限
                requestPermission();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        initData(savedInstanceState);
        initListener();
    }

    /**
     * 初始化监听
     *
     * @User: xiemiao
     * @Time: 2017 -07-13
     * @Date: 16 :45:24
     */
    public abstract void initListener();

    /**
     * 初始化沉浸式状态栏
     *
     * @User: xiemiao
     * @Time: 2017 -10-16
     * @Date: 00 :09:15
     */
    private void initImmersionBar() {
        if (mTopContainerLayout != null) {
            if (this instanceof MainActivity) {
                ImmersionBar.with(this).titleBar(mTopContainerLayout).init();//是登录界面,状态栏设置为透明
            } else if (this instanceof LoginActivity) {
                ImmersionBar.with(this).titleBar(mTopContainerLayout).init();//是主界面,状态栏设置为透明
            }
            // else if (this instanceof SelectCityActivity) {
            //                //是选择城市界面,不做处理
            //            } else if (this instanceof SearchEnterpriseListActivity) {
            //                //是搜索企业圈界面,不做处理
            //            }
            else {
                ImmersionBar.with(this).titleBar(mTopContainerLayout).statusBarColor(R.color.colorPrimary).init();
            }
        }
    }

    /*================对顶部标题,土司,进度条等的相关操作===start==============*/


    /**
     * 功能:初始化标题栏UI
     */
    private void initCommonTitleView() {
        mTopContainerLayout = (LinearLayout) findViewById(R.id.ll_container);
        mTopLayout = (RelativeLayout) findViewById(R.id.common_title_layout);
        if (mTopLayout != null) {
            mTitleText = (TextView) findViewById(R.id.tv_title);
            mTitleSmall = (TextView) findViewById(R.id.tv_title_small);
            mBack = (Button) findViewById(R.id.btn_title_left);
            mTitleLeftText = (TextView) findViewById(R.id.tv_title_left);
            mTitleRightText = (TextView) findViewById(R.id.tv_title_right);
            mTitleRightImage = (ImageView) findViewById(R.id.iv_title_right);
            mTitleLeftImage = (ImageView) findViewById(R.id.iv_title_left);
        }
    }

    /**
     * 功能:显示toast
     *
     * @param resId
     */
    public void showToast(int resId) {
        if (resId != 0) {
            cancelToast();
            toast = Toast.makeText(cContext, resId, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /**
     * 功能:显示toast
     *
     * @param content
     */
    public void showToast(String content) {
        if (cContext != null) {
            toast = Toast.makeText(cContext, content, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /**
     * 功能:关闭toast
     */
    public void cancelToast() {
        if (toast != null) {
            toast.cancel();
        }
    }

    /**
     * 功能: 判断进度条是否显示
     *
     * @return
     */
    public boolean isProgressShowing() {
        return (progressDialog != null) && (progressDialog.isShowing());
    }

    /**
     * 功能:显示Progress
     */
    public void showProgress() {
        if (null == progressDialog) {
            progressDialog = new Dialog(this, R.style.DialogStyle);// loading_dialog
            progressDialog.setContentView(R.layout.common_dialog_layout);
            ProgressBar progressBar = (ProgressBar) progressDialog.findViewById(R.id.progressBar);

            if (android.os.Build.VERSION.SDK_INT > 22) {//android 6.0替换clip的加载动画
                final Drawable drawable = cContext.getApplicationContext().getResources().getDrawable(R.drawable.common_loading_anim_up6_);
                progressBar.setIndeterminateDrawable(drawable);
            }

            progressDialog.setCancelable(true);
            // 设置dialog不消失,下面的activity还有焦点
            Window dialogWindow = progressDialog.getWindow();
            dialogWindow.setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager
                    .LayoutParams.FLAG_NOT_FOCUSABLE);
            progressDialog.setCanceledOnTouchOutside(true);
            progressDialog.show();
        } else {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            progressDialog.setCancelable(true);
            if (!isFinishing()) {
                progressDialog.show();
            }
        }
    }

    /**
     * 功能:关闭dialog
     */
    public void cancelProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog.cancel();
        }
    }

    /**
     * 功能:返回按钮显示
     */
    public void setLeftButton() {
        mBack.setVisibility(View.VISIBLE);
        mBack.setOnClickListener(this);
    }

    /**
     * 功能:设置标题
     *
     * @param text
     */
    public void setTitleText(String text) {
        mTitleText.setText(text);
        mTitleText.setVisibility(View.VISIBLE);
        mTitleText.setOnClickListener(this);
    }

    /**
     * 功能:设置标题旁边的小文本
     *
     * @param text
     */
    public void setTitleSmallText(String text) {
        mTitleSmall.setText(text);
        mTitleSmall.setVisibility(View.VISIBLE);
        mTitleSmall.setOnClickListener(this);
    }

    public TextView getTitleTextView() {
        return mTitleText;
    }

    /**
     * 功能:设置标题
     *
     * @param strId
     */
    public void setTitleText(int strId) {
        setTitleText(getString(strId));
    }

    /**
     * 功能:标题栏右边文字设置
     *
     * @param text
     */
    public void setTitleRightText(String text) {
        mTitleRightText.setText(text);
        //        mTitleRightText.setOnClickListener(this);
        mTitleRightText.setVisibility(View.VISIBLE);
    }

    /**
     * 功能:隐藏标题栏右边文字
     */
    public void setTitleRightTextHint() {
        mTitleRightText.setVisibility(View.GONE);
    }

    /**
     * 功能:标题栏右边文字设置
     *
     * @param strId
     */
    public void setTitleRightText(int strId) {
        setTitleRightText(getString(strId));
    }

    /**
     * 功能:标题栏左边边文字设置
     *
     * @param text
     */
    public void setTitleLeftText(String text) {
        mTitleLeftText.setText(text);
        mTitleLeftText.setOnClickListener(this);
        mTitleLeftText.setVisibility(View.VISIBLE);
    }

    /**
     * 功能:标题栏左边文字设置
     *
     * @param strId
     */
    public void setTitleLeftText(int strId) {
        setTitleLeftText(getString(strId));
    }

    /**
     * 功能:标题栏右边图片设置
     *
     * @param resId
     */
    public void setTitleRightImage(int resId) {
        mTitleRightImage.setBackgroundResource(resId);
        mTitleRightImage.setVisibility(View.VISIBLE);
    }

    /**
     * 功能：设置标题栏右图片按钮的显示和隐藏 描述：
     *
     * @param isHint true 隐藏 false 显示
     * @author：xiemiao
     * @date:2017年4月8日
     */
    public void setTitleRightImageHint(boolean isHint) {
        if (isHint) {
            mTitleRightImage.setVisibility(View.GONE);
        } else {
            mTitleRightImage.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 功能:标题栏左边图片设置
     *
     * @param resId
     */
    public void setTitleLeftImage(int resId) {
        mTitleLeftImage.setBackgroundResource(resId);
        mTitleLeftImage.setVisibility(View.VISIBLE);
        mTitleLeftImage.setOnClickListener(this);
    }

    /**
     * hintTopLayout:(隐藏顶部标题布局). <br/>
     *
     * @author xiemiao
     */
    public void hintTopLayout() {
        if (mTopContainerLayout != null) {
            mTopContainerLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_title_left:
                // 隐藏键盘
                InputMethodManager imm = (InputMethodManager) getSystemService(Context
                        .INPUT_METHOD_SERVICE);
                boolean isOpen = imm.isActive();// isOpen若返回true，则表示输入法打开
                View currentFocus = this.getCurrentFocus();
                if (currentFocus != null) {
                    if (isOpen) {
                        imm.hideSoftInputFromWindow(currentFocus.getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                }
                finish();
                break;

            default:
                break;
        }
    }

    /**
     * showTishiDialog:(展示提示对话框). <br/>
     *
     * @param message
     * @author xiemiao
     */
    public void showTishiDialog(String message) {
        AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setMessage(message);
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "我知道了", new DialogInterface
                .OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
        Button btnNegative = dialog.getButton(android.app.AlertDialog.BUTTON_NEGATIVE);
        btnNegative.setTextColor(getResources().getColor(R.color.coral));
    }


    /**
     * 展示确认,取消dialog
     *
     * @param message
     * @author xiemiao
     */
    public void showConfirmOrCancelDialog(String title, String message, View.OnClickListener clickListener) {
        View view = View.inflate(this, R.layout.dialog_confirm_or_cancel, null);
        TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        TextView tvContent = (TextView) view.findViewById(R.id.tvContent);
        TextView tvNo = (TextView) view.findViewById(R.id.tvNo);
        TextView tvYes = (TextView) view.findViewById(R.id.tvYes);

        tvTitle.setText(title);
        tvContent.setText(message);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(true);
        tvNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        tvYes.setOnClickListener(clickListener);
        dialog.show();
    }

    /**
     * 展示确认,取消dialog
     *
     * @param message
     * @author xiemiao
     */
    public AlertDialog showConfirmOrCancelDialog(String title, String message, String cancelText, String confirmText, View.OnClickListener clickListener) {
        View view = View.inflate(this, R.layout.dialog_confirm_or_cancel, null);
        TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        TextView tvContent = (TextView) view.findViewById(R.id.tvContent);
        TextView tvNo = (TextView) view.findViewById(R.id.tvNo);
        TextView tvYes = (TextView) view.findViewById(R.id.tvYes);

        tvTitle.setText(title);
        tvContent.setText(message);
        tvNo.setText(cancelText);
        tvYes.setText(confirmText);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(true);
        tvNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        tvYes.setOnClickListener(clickListener);
        dialog.show();

        return dialog;
    }


    private long mPreTimeMillis;

    @Override // 统一退出控制
    public void onBackPressed() {
        // 如果是入口,就去执行2秒内按返回键执行销毁
        if (this instanceof MainActivity) {
            if (System.currentTimeMillis() - mPreTimeMillis > 2000) {
                Toast.makeText(BaseActivity.this, "再按一次,退出应用", Toast.LENGTH_SHORT).show();
                mPreTimeMillis = System.currentTimeMillis();
                return;// 间隔在2秒之外就直接return,不执行下面的销毁
            } else {
                finish();
                System.exit(0);
            }
        }

        // 不是入口,就直接执行销毁
        super.onBackPressed();// finish
    }

    /*================对顶部标题,土司,进度条等的相关操作===end==============*/

         /*================安卓6.0运行时权限申请相关=======start==========*/

    /**
     * 请求运行时权限(安卓6.0)
     *
     * @User: xiemiao
     * @Time: 2017 -07-18
     * @Date: 11 :57:05
     */
    private void requestPermission() {
        //所要申请的权限
        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION};
        if (EasyPermissions.hasPermissions(this, perms)) {//检查是否获取该权限
            Timber.i("已获取权限:" + Arrays.toString(perms));
        } else {
            //第二个参数是被拒绝后再次申请该权限的解释
            //第三个参数是请求码
            //第四个参数是要申请的权限
            EasyPermissions.requestPermissions(this, "必要的权限", 0, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //把申请权限的回调交由EasyPermissions处理
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    //下面两个方法是实现EasyPermissions的EasyPermissions.PermissionCallbacks接口
    //分别返回授权成功和失败的权限
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Log.i("PermissionTag", "获取成功的权限:" + perms.toString());
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.i("PermissionTag", "获取失败的权限:" + perms.toString());
    }

     /*================安卓6.0运行时权限申请相关=======end==========*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY)
            mUnbinder.unbind();
        this.mUnbinder = null;
        if (mPresenter != null)
            mPresenter.onDestroy();//释放资源
        this.mPresenter = null;
    }

    /**
     * 是否使用eventBus,默认为使用(true)，
     *
     * @return
     */
    @Override
    public boolean useEventBus() {
        return true;
    }

    /**
     * 这个Activity是否会使用Fragment,框架会根据这个属性判断是否注册{@link android.support.v4.app.FragmentManager.FragmentLifecycleCallbacks}
     * 如果返回false,那意味着这个Activity不需要绑定Fragment,那你再在这个Activity中绑定继承于 {@link com.jess.arms.base.BaseFragment} 的Fragment将不起任何作用
     *
     * @return
     */
    @Override
    public boolean useFragment() {
        return true;
    }
}

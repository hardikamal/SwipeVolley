package com.deardhruv.swipevolley;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;

import com.deardhruv.swipevolley.ImageListFrag.ShareViewItem;

public class MainActivity extends FragmentActivity implements ShareViewItem {

	SectionsPagerAdapter	mSectionsPagerAdapter;
	ViewPager				mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	Bundle				arg;
	ImagePreviewFrag	previewFrag;

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			Fragment fragment = null;
			if (position == 1) {
				fragment = new ImagePreviewFrag(mCallback);
				try {
					// mCallback = (IUpdateImageView) ImagePreviewFrag.class;
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
				// if (arg != null) {
				// fragment.setArguments(arg);
				// }
				previewFrag = (ImagePreviewFrag) fragment;
			}
			if (position == 0) {
				fragment = new ImageListFrag();

			}

			return fragment;
		}

		@Override
		public int getCount() {
			// Show 2 total pages.
			return 2;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			switch (position)
			{
			case 0:
				return "Image List";
			case 1:
				return "Image Preview";
			}
			return null;
		}
	}

	IUpdateImageView	mCallback	= null;

	public interface IUpdateImageView {
		public void updateImagePreview(ItemDetail sharedItem);
	}

	public void shareItem(final ItemDetail sharedItem) {
		// arg = new Bundle();
		// arg.putSerializable("item", sharedItem);

		mViewPager.setCurrentItem(1, true);

		if (previewFrag != null) {
			previewFrag.updateImagePreview(sharedItem);
		}

		// new Handler().postDelayed(new Runnable() {
		// public void run() {
		// if (mCallback != null)
		// mCallback.updateImagePreview(sharedItem);
		// }
		// }, 1000);
	}
}

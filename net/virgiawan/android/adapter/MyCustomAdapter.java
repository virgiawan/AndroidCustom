package net.virgiawan.android.adapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class MyCustomAdapter<T> extends BaseAdapter {

	private List<T> list;
	private List<T> shown;
	private MyFilterAdapter<T> filter;
	
	public abstract ViewHolder<T> createHolder();
	
	public MyCustomAdapter(){
		super();
		this.list = new ArrayList<T>();
		this.shown = new ArrayList<T>();
	}
	
	@Override
	public int getCount() {
		return shown.size();
	}

	@Override
	public T getItem(int position) {
		return shown.get(position);
	}

	@Override
	public View getView(int pos, View view, ViewGroup parent) {
		ViewHolder<T> holder = null;
		if (view == null) {
			holder = createHolder();
			view = holder.createView(parent.getContext());
			view.setTag(holder);
		} else {
			holder = extractHolder(view);
		}

		holder.bind(pos, getItem(pos));

		return view;
	}
	
	@SuppressWarnings("unchecked")
	public ViewHolder<T> extractHolder(View view){
		return (ViewHolder<T>) view.getTag();
	}
	
	public List<T> getList(){
		return this.list;
	}
	
	public void setList(List<T> list){
		if(list == null){
			this.list = Collections.<T> emptyList(); 
		}
		else{
			this.list = list;
		}
	} 
	
	public void prepandList(List<T> list){
		int i = 0;
		for (T item : list) {
			this.list.add(i, item);
			i++;
		}
	} 
	
	public void appendList(List<T> list){
		this.list.addAll(list);
	}
	
	public MyFilterAdapter<T> getFilter(){
		return this.filter;
	}
	
	public void setFilter(MyFilterAdapter<T> filter){
		this.filter = filter;
	}
	
	@Override
	public void notifyDataSetChanged() {
		if(filter==null){
			shown = list;
		}
		else{
			shown.clear();
			for (T item : list) {
				if(filter.isFiltered(item)){
					shown.add(item);
				}
			}
		}
		super.notifyDataSetChanged();
	}
	
	// class holder
	public static abstract class ViewHolder<T> {
		public abstract View createView(Context context);

		public abstract void bind(int pos, T object);
	}
	
}

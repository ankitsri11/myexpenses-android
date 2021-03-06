package br.com.jonathanzanella.myexpenses.overview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.text.NumberFormat;

import br.com.jonathanzanella.myexpenses.R;
import br.com.jonathanzanella.myexpenses.expense.ExpenseWeeklyOverviewAdapter;
import br.com.jonathanzanella.myexpenses.expense.Expense;
import br.com.jonathanzanella.myexpenses.views.BaseView;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Jonathan Zanella on 14/02/16.
 */
@SuppressLint("ViewConstructor")
class OverviewExpensesWeeklyView extends BaseView {
	@Bind(R.id.view_overview_expenses_weekly_list)
	RecyclerView list;
	@Bind(R.id.view_overview_expenses_weekly_total)
	TextView total;

	private ExpenseWeeklyOverviewAdapter adapter;
	private WeeklyPagerAdapter.Period period;

	public OverviewExpensesWeeklyView(Context context, WeeklyPagerAdapter.Period period) {
		super(context);
		this.period = period;
	}

	@Override
	protected void init() {
		inflate(getContext(), R.layout.view_overview_expenses_weekly, this);
		ButterKnife.bind(this);

		adapter = new ExpenseWeeklyOverviewAdapter();

		list.setAdapter(adapter);
		list.setHasFixedSize(true);
		list.setLayoutManager(new LinearLayoutManager(getContext()));
	}

	@Override
	public void refreshData() {
		super.refreshData();

		adapter.setExpenses(Expense.expenses(period));
		adapter.notifyDataSetChanged();

		total.setText(NumberFormat.getCurrencyInstance().format(adapter.getTotalValue() / 100.0));
	}
}
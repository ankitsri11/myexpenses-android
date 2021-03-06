package br.com.jonathanzanella.myexpenses.bill;

import com.raizlabs.android.dbflow.StringUtils;

import java.io.IOException;
import java.util.List;

import br.com.jonathanzanella.myexpenses.account.AccountApi;
import br.com.jonathanzanella.myexpenses.log.Log;
import br.com.jonathanzanella.myexpenses.server.Server;
import br.com.jonathanzanella.myexpenses.sync.UnsyncModel;
import br.com.jonathanzanella.myexpenses.sync.UnsyncModelApi;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by jzanella on 6/12/16.
 */
public class BillApi implements UnsyncModelApi<Bill> {
	private static final String LOG_TAG = AccountApi.class.getSimpleName();
	private BillInterface BillInterface;

	private BillInterface getInterface() {
		if(BillInterface == null)
			BillInterface = new Server().billInterface();
		return BillInterface;
	}

	@Override
	public List<Bill> index() {
		Call<List<Bill>> caller = getInterface().index(Bill.greaterUpdatedAt());

		try {
			Response<List<Bill>> response = caller.execute();
			if(response.isSuccessful()) {
				return response.body();
			} else {
				Log.error(LOG_TAG, "Index request error: " + response.message());
			}
		} catch (IOException e) {
			Log.error(LOG_TAG, "Index request error: " + e.getMessage());
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void save(UnsyncModel model) {
        Bill bill = (Bill) model;
        Call<Bill> caller;
        if(StringUtils.isNotNullOrEmpty(bill.getServerId()))
            caller = getInterface().update(bill.getServerId(), bill);
        else
            caller = getInterface().create(bill);

        try {
            Response<Bill> response = caller.execute();
            if(response.isSuccessful()) {
                model.syncAndSave(response.body());
	            Log.info(LOG_TAG, "Updated: " + bill.getData());
            } else {
                Log.error(LOG_TAG, "Save request error: " + response.message() + " uuid: " + bill.getUuid());
            }
        } catch (IOException e) {
	        Log.error(LOG_TAG, "Save request error: " + e.getMessage() + " uuid: " + bill.getUuid());
            e.printStackTrace();
        }
	}

	@Override
	public List<Bill> unsyncModels() {
		return Bill.unsync();
	}

	@Override
	public long greaterUpdatedAt() {
		return Bill.greaterUpdatedAt();
	}
}
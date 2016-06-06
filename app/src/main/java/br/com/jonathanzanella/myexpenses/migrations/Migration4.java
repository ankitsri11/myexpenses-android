package br.com.jonathanzanella.myexpenses.migrations;

import com.raizlabs.android.dbflow.annotation.Migration;
import com.raizlabs.android.dbflow.sql.SQLiteType;
import com.raizlabs.android.dbflow.sql.migration.AlterTableMigration;

import br.com.jonathanzanella.myexpenses.database.MyDatabase;
import br.com.jonathanzanella.myexpenses.models.Source;

/**
 * Created by Jonathan Zanella on 12/02/16.
 */
@Migration(version = 6, database = MyDatabase.class)
public class Migration4 extends AlterTableMigration<Source> {

	public Migration4() {
		super(Source.class);
	}

	@Override
	public void onPreMigrate() {
		// Simple ALTER TABLE migration wraps the statements into a nice builder notation
		addColumn(SQLiteType.TEXT, "serverId");
		addColumn(SQLiteType.INTEGER, "createdAt");
		addColumn(SQLiteType.INTEGER, "updatedAt");
	}
}
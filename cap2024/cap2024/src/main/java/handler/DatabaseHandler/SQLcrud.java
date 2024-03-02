package handler.DatabaseHandler;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class SQLcrud extends SQLBase {

    private final String insertSqlText;
    private final String selectSqlText;
    private final String updateSqlText;
    private final String deleteSqlText;

    public SQLcrud(SQLcrud query) {
        this(DBHandler.GetInstance(), query);
    }

    public SQLcrud(DBHandler handler, SQLcrud query) {
        super(handler);
        this.insertSqlText = query.insertSqlText;
        this.selectSqlText = query.selectSqlText;
        this.updateSqlText = query.updateSqlText;
        this.deleteSqlText = query.deleteSqlText;
    }
    public ArrayList<ArrayList<Object>> ReadUseTransactionToArrayList(Object[] args) {
        ArrayList<ArrayList<Object>> ret = new ArrayList<>();

        try {
            handler.OpenTransaction();
            handler.CommitTransaction();

            ResultSet resultSet = Read(args);

            // ResultSet을 ArrayList에 저장
            while (resultSet.next()) {
                ArrayList<Object> row = new ArrayList<>();
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    row.add(resultSet.getObject(i));
                }
                ret.add(row);
            }

            resultSet.close();
            handler.CommitTransaction();
        } catch (Exception ex) {
            handler.RollbackTransaction();
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage()); // Exception 메시지를 표시
        }
        return ret;
    }
    public ArrayList<ArrayList<Object>> ReadUseNotTransactionToArrayList(Object[] args) {
        ArrayList<ArrayList<Object>> ret = new ArrayList<>();

        try {
            ResultSet resultSet = Read(args);

            // ResultSet을 ArrayList에 저장
            while (resultSet.next()) {
                ArrayList<Object> row = new ArrayList<>();
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    row.add(resultSet.getObject(i));
                }
                ret.add(row);
            }

            resultSet.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage()); // Exception 메시지를 표시
        }

        return ret;
    }

    protected ResultSet Read(Object[] args) throws Exception {
        try {
            return Read(handler.CreateCommand(selectSqlText, args));
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        }
    }

    protected ResultSet Read(PreparedStatement preparedStatement) throws Exception {
        try {
            ResultSet reader = preparedStatement.executeQuery();
            preparedStatement.close();
            return reader;
        } catch (Exception ex) {
            Exception exception = ex;
            if (preparedStatement != null) {
                exception = new Exception(preparedStatement.getConnection() + System.lineSeparator() +
                        exception.getMessage(), exception);
            }
            throw exception;
        }
    }
}

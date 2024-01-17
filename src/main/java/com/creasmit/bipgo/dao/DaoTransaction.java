/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.dao;

import com.creasmit.bipgo.adapter.TransactionAdapter;
import com.creasmit.bipgo.adapter.WalletAdapter;
import com.creasmit.bipgo.callback.TransactionCallback;
import com.creasmit.bipgo.entity.Compte;
import com.creasmit.bipgo.entity.Devise;
import com.creasmit.bipgo.entity.StatutTransaction;
import com.creasmit.bipgo.entity.Transaction;
import com.creasmit.bipgo.entity.TypeOperation;
import com.creasmit.bipgo.entity.Wallet;
import com.creasmit.bipgo.model.AmountReport;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import one.creas.emalib.hbd.IDaoGeneric;
import one.creas.emalib.util.Log;
import one.creas.emalib.util.QueryParam;
import org.hibernate.Session;

/**
 *
 * @author georges
 */
public class DaoTransaction implements IDaoTransaction {

    private IDaoGeneric dao;

    public DaoTransaction() {
    }

    public void setDao(IDaoGeneric dao) {
        this.dao = dao;
    }

    @Override
    public List<TransactionAdapter> listing() {
        try {
            String sql = "SELECT "
                    + "t.id tid,"
                    + "t.exp,"
                    + "t.ben,"
                    + "t.montant,"
                    + "t.date,"
                    + "t.ref,"
                    + "ty.id oid,"
                    + "ty.description topdes,"
                    + "s.id sid,"
                    + "s.description,"
                    + "d.id did,"
                    + "d.libelle "
                    + "FROM "
                    + "Transaction t "
                    + "JOIN TypeOperation ty ON ty.id = t.fkTypeOperation "
                    + "JOIN StatutTransaction s ON s.id = t.fkStatutTransaction "
                    + "JOIN Devise d ON d.id = t.fkDevise";

            List<Object[]> objects = this.dao.selectSQL(sql, new QueryParam(null, null));
            List<TransactionAdapter> transanctionAdapter = new ArrayList<>();

            for (Object[] o : objects) {

                Transaction t = new Transaction();
                t.setId(Integer.parseInt(o[0].toString()));
                try {
                    t.setExp(o[1].toString());
                } catch (Exception e) {
                }
                try {
                    t.setBen(o[2].toString());
                } catch (Exception e) {
                }
                try {
                    t.setMontant(Double.parseDouble(o[3].toString()));
                } catch (Exception e) {
                }
                try {
                    t.setDate((Date) o[4]);
                } catch (Exception e) {
                }

                try {
                    t.setRef(o[5].toString());
                } catch (Exception e) {
                }

                TypeOperation ty = new TypeOperation();
                try {
                    ty.setId(Integer.parseInt(o[6].toString()));
                } catch (Exception e) {

                }
                try {
                    ty.setDescription(o[7].toString());
                } catch (Exception e) {

                }

                StatutTransaction s = new StatutTransaction();
                try {
                    s.setId(Integer.parseInt(o[8].toString()));
                } catch (Exception e) {

                }
                try {
                    s.setDescription(o[9].toString());
                } catch (Exception e) {

                }

                Devise d = new Devise();
                try {
                    d.setId(Integer.parseInt(o[10].toString()));
                } catch (Exception e) {

                }
                try {
                    d.setLibelle(o[11].toString());
                } catch (Exception e) {

                }

                TransactionAdapter ta = new TransactionAdapter();
                ta.setTransaction(t);
                ta.setTypeOperation(ty);
                ta.setStatutTransaction(s);
                ta.setDevise(d);
                transanctionAdapter.add(ta);

            }
            return transanctionAdapter;
        } catch (Exception e) {
            Log.i(this, e);
        }
        return new ArrayList<>();
    }

    @Override
    public List<TransactionAdapter> listing(QueryParam... queryParams) {
        try {
            String sql = "SELECT"
                    + "t.id tid,"
                    + "t.exp,"
                    + "t.ben,"
                    + "t.montant,"
                    + "t.date,"
                    + "t.ref,"
                    + "ty.id oid,"
                    + "ty.description topdes,"
                    + "s.id sid,"
                    + "s.description,"
                    + "d.id did,"
                    + "d.libelle"
                    + "FROM "
                    + "Transaction t "
                    + "JOIN TypeOperation ty ON ty.id = t.fkTypeOperation "
                    + "JOIN StatutTransaction s ON s.id = t.fkStatutTransaction "
                    + "JOIN Devise d ON d.id = t.fkDevise WHERE ";

            int comp = 0;
            for (QueryParam queryParam : queryParams) {
                if (comp == 0) {
                    sql += " " + queryParam.getParam() + ":" + queryParam.getValue();
                } else {
                    sql += " and " + queryParam.getParam() + "=" + queryParam.getValue();
                }
                comp++;
            }
            List<Object[]> objects = this.dao.selectSQL(sql, new QueryParam(null, null));
            List<TransactionAdapter> transanctionAdapter = new ArrayList<>();

            for (Object[] o : objects) {
                Transaction t = new Transaction();
                t.setId(Integer.parseInt(o[0].toString()));
                try {
                    t.setExp(o[1].toString());
                } catch (Exception e) {
                }
                try {
                    t.setBen(o[2].toString());
                } catch (Exception e) {
                }
                try {
                    t.setMontant(Double.parseDouble(o[3].toString()));
                } catch (Exception e) {
                }
                try {
                    t.setDate((Date) o[4]);
                } catch (Exception e) {
                }
                try {
                    t.setRef(o[5].toString());
                } catch (Exception e) {
                }

                TypeOperation ty = new TypeOperation();
                try {
                    ty.setId(Integer.parseInt(o[6].toString()));
                } catch (Exception e) {

                }
                try {
                    ty.setDescription(o[7].toString());
                } catch (Exception e) {

                }

                StatutTransaction s = new StatutTransaction();
                try {
                    s.setId(Integer.parseInt(o[8].toString()));
                } catch (Exception e) {

                }
                try {
                    s.setDescription(o[9].toString());
                } catch (Exception e) {

                }

                Devise d = new Devise();
                try {
                    d.setId(Integer.parseInt(o[10].toString()));
                } catch (Exception e) {

                }
                try {
                    d.setLibelle(o[11].toString());
                } catch (Exception e) {

                }

                TransactionAdapter ta = new TransactionAdapter();
                ta.setTransaction(t);
                ta.setTypeOperation(ty);
                ta.setStatutTransaction(s);
                ta.setDevise(d);
                transanctionAdapter.add(ta);

            }
            return transanctionAdapter;
        } catch (Exception e) {
            Log.i(this, e);
        }
        return new ArrayList<>();
    }

    @Override
    public List<TransactionAdapter> listing(String dateFrom, String dateTo, TypeOperation typeOperation) {
        try {
            String sql = "SELECT "
                    + "t.id tid,"
                    + "t.exp,"
                    + "t.ben,"
                    + "t.date,"
                    + "t.montant,"
                    + "t.ref,"
                    + "top.id topid,"
                    + "top.description topdes,"
                    + "st.id stid,"
                    + "st.description, "
                    + "d.id did,"
                    + "d.libelle"
                    + " FROM Transaction t "
                    + "LEFT JOIN TypeOperation top ON top.id=t.fkTypeOperation "
                    + "LEFT JOIN Devise d ON d.id = t.fkDevise "
                    + "LEFT JOIN StatutTransaction st ON st.id=t.fkStatutTransaction WHERE DATE_FORMAT(date,'%d/%m/%Y')>=:dateFrom and  DATE_FORMAT(date,'%d/%m/%Y')<=:dateTo and top.id=:tpid";

            List<Object[]> objects = this.dao.selectSQL(sql, new QueryParam("dateFrom", dateFrom),
                    new QueryParam("dateTo",
                            dateTo), new QueryParam("tpid", typeOperation.getId()));

            List<TransactionAdapter> transactionAdapter = new ArrayList<>();

            for (Object[] o : objects) {
                Transaction t = new Transaction();
                t.setId(Integer.parseInt(o[0].toString()));
                try {
                    t.setExp(o[1].toString());
                } catch (Exception e) {
                }
                try {
                    t.setBen(o[2].toString());
                } catch (Exception e) {
                }
                try {
                    t.setDate((Date) o[3]);
                } catch (Exception e) {
                }
                try {
                    t.setMontant(Double.parseDouble(o[4].toString()));
                } catch (Exception e) {
                }

                try {
                    t.setRef(o[5].toString());
                } catch (Exception e) {
                }

                TypeOperation ty = new TypeOperation();
                try {
                    ty.setId(Integer.parseInt(o[6].toString()));
                } catch (Exception e) {

                }
                try {
                    ty.setDescription(o[7].toString());
                } catch (Exception e) {

                }

                StatutTransaction s = new StatutTransaction();
                try {
                    ty.setId(Integer.parseInt(o[8].toString()));
                } catch (Exception e) {

                }
                try {
                    ty.setDescription(o[9].toString());
                } catch (Exception e) {

                }
                Devise d = new Devise();
                try {
                    d.setId(Integer.parseInt(o[10].toString()));
                } catch (Exception e) {

                }
                try {
                    d.setLibelle(o[11].toString());
                } catch (Exception e) {

                }

                TransactionAdapter ta = new TransactionAdapter();
                ta.setTransaction(t);
                ta.setTypeOperation(ty);
                ta.setStatutTransaction(s);
                ta.setDevise(d);
                transactionAdapter.add(ta);

            }
            return transactionAdapter;
        } catch (Exception e) {
            Log.i(this, e);
        }
        return new ArrayList<>();
    }

    @Override
    public List<TransactionAdapter> listing(String dateFrom, String dateTo, Devise devise) {
        try {
            String sql = "SELECT "
                    + "t.id tid,"
                    + "t.exp,"
                    + "t.ben,"
                    + "t.date,"
                    + "t.montant,"
                    + "t.ref,"
                    + "top.id topid,"
                    + "top.description,"
                    + "st.id stid,"
                    + "st.description, "
                    + "d.id did, "
                    + "d.libelle, "
                    + " FROM Transaction t "
                    + "LEFT JOIN TypeOperation top ON top.id=t.fkTypeOperation "
                    + "LEFT JOIN Devise d ON d.id=t.fkDevise"
                    + "LEFT JOIN StatutTransaction st ON st.id=t.fkStatutTransaction WHERE DATE_FORMAT(date,'%d/%m/%Y')>=:dateFrom and  DATE_FORMAT(date,'%d/%m/%Y')<=:dateTo and t.libele=:devise";

            List<Object[]> objects = this.dao.selectSQL(sql, new QueryParam("dateFrom", dateFrom),
                    new QueryParam("dateTo",
                            dateTo), new QueryParam("devise", devise.getLibelle()));

            List<TransactionAdapter> transactionAdapter = new ArrayList<>();

            for (Object[] o : objects) {
                Transaction t = new Transaction();
                t.setId(Integer.parseInt(o[0].toString()));
                try {
                    t.setExp(o[1].toString());
                } catch (Exception e) {
                }
                try {
                    t.setBen(o[2].toString());
                } catch (Exception e) {
                }
                try {
                    t.setDate((Date) o[3]);
                } catch (Exception e) {
                }
                try {
                    t.setMontant(Double.parseDouble(o[4].toString()));
                } catch (Exception e) {
                }

                try {
                    t.setRef(o[5].toString());
                } catch (Exception e) {
                }

                TypeOperation ty = new TypeOperation();
                try {
                    ty.setId(Integer.parseInt(o[6].toString()));
                } catch (Exception e) {

                }
                try {
                    ty.setDescription(o[7].toString());
                } catch (Exception e) {

                }

                StatutTransaction s = new StatutTransaction();
                try {
                    ty.setId(Integer.parseInt(o[8].toString()));
                } catch (Exception e) {

                }
                try {
                    ty.setDescription(o[9].toString());
                } catch (Exception e) {

                }
                Devise d = new Devise();
                try {
                    d.setId(Integer.parseInt(o[8].toString()));
                } catch (Exception e) {

                }
                try {
                    d.setLibelle(o[9].toString());
                } catch (Exception e) {

                }

                TransactionAdapter ta = new TransactionAdapter();
                ta.setTransaction(t);
                ta.setTypeOperation(ty);
                ta.setStatutTransaction(s);
                ta.setDevise(d);
                transactionAdapter.add(ta);

            }
            return transactionAdapter;
        } catch (Exception e) {
            Log.i(this, e);
        }
        return new ArrayList<>();
    }

    @Override
    public List<TransactionAdapter> listing(String dateFrom, String dateTo, TypeOperation typeOperation, Devise devise) {
        try {
            String sql = "SELECT "
                    + "t.id tid,"
                    + "t.exp,"
                    + "t.ben,"
                    + "t.date,"
                    + "t.montant,"
                    + "t.ref,"
                    + "top.id topid,"
                    + "top.description,"
                    + "st.id stid,"
                    + "st.description, "
                    + "d.id did, "
                    + "d.libelle "
                    + " FROM Transaction t "
                    + "LEFT JOIN TypeOperation top ON top.id=t.fkTypeOperation "
                    + "LEFT JOIN Devise d ON d.id=t.fkDevise "
                    + "LEFT JOIN StatutTransaction st ON st.id=t.fkStatutTransaction WHERE DATE_FORMAT(date,'%d/%m/%Y')>=:dateFrom and  DATE_FORMAT(date,'%d/%m/%Y')<=:dateTo and t.libele=:devise and top.id=:tpid";

            List<Object[]> objects = this.dao.selectSQL(sql, new QueryParam("dateFrom", dateFrom),
                    new QueryParam("dateTo",
                            dateTo), new QueryParam("tpid", typeOperation.getId()), new QueryParam("devise", devise.getLibelle()));

            List<TransactionAdapter> transactionAdapter = new ArrayList<>();

            for (Object[] o : objects) {
                Transaction t = new Transaction();
                t.setId(Integer.parseInt(o[0].toString()));
                try {
                    t.setExp(o[1].toString());
                } catch (Exception e) {
                }
                try {
                    t.setBen(o[2].toString());
                } catch (Exception e) {
                }
                try {
                    t.setDate((Date) o[3]);
                } catch (Exception e) {
                }
                try {
                    t.setMontant(Double.parseDouble(o[4].toString()));
                } catch (Exception e) {
                }

                try {
                    t.setRef(o[5].toString());
                } catch (Exception e) {
                }

                TypeOperation ty = new TypeOperation();
                try {
                    ty.setId(Integer.parseInt(o[6].toString()));
                } catch (Exception e) {

                }
                try {
                    ty.setDescription(o[7].toString());
                } catch (Exception e) {

                }

                StatutTransaction s = new StatutTransaction();
                try {
                    ty.setId(Integer.parseInt(o[8].toString()));
                } catch (Exception e) {

                }
                try {
                    ty.setDescription(o[9].toString());
                } catch (Exception e) {

                }
                Devise d = new Devise();
                try {
                    d.setId(Integer.parseInt(o[10].toString()));
                } catch (Exception e) {

                }
                try {
                    d.setLibelle(o[11].toString());
                } catch (Exception e) {

                }

                TransactionAdapter ta = new TransactionAdapter();
                ta.setTransaction(t);
                ta.setTypeOperation(ty);
                ta.setStatutTransaction(s);
                ta.setDevise(d);
                transactionAdapter.add(ta);

            }
            return transactionAdapter;
        } catch (Exception e) {
            Log.i(this, e);
        }
        return new ArrayList<>();
    }

    @Override
    public Transaction update(Transaction transanction
    ) {
        try {
            return (Transaction) this.dao.modify(transanction);
        } catch (Exception e) {

        }
        return null;
    }

    @Override
    public AmountReport amountReport(Wallet wallet, TypeOperation typeOperation, Devise devise, String date) {
        try {
            String sql = "SELECT "
                    + "SUM(montant) "
                    + "FROM "
                    + "Transaction WHERE ben='" + wallet.getBipid() + "' and fkTypeOperation=" + typeOperation.getId() + " and DATE_FORMAT(date,'%d-%m-%Y')=DATE_FORMAT('"+date+"','%d-%m-%Y') and fkStatutTransaction=3";

            Log.i(this, sql);
            List<Object[]> objects = this.dao.selectSQL(sql);
            Log.i(this, new Gson().toJson(objects));
            AmountReport ar = new AmountReport();
            for (Object o : objects) {

                try {
                    ar.setMontant(Double.parseDouble(o.toString()));
                } catch (Exception e) {

                }

                return ar;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public void initTransaction(WalletAdapter from, WalletAdapter to, Transaction transaction, TransactionCallback transactionCallback) {

        Session session = null;
        try {
            session = this.dao.getSession();
            session.getSession().getTransaction().begin();
            int suchOperation = 3;

            Log.i(this, "is Processing...");

            //Processing...
            if (from.getListeDeComptes().size() > 0) {

                /**
                 * Getting respectively sender and recipient wallet account
                 */
                Compte compteSender = from.getListeDeComptes().get(0).getCompte();
                Compte compteRecipient = to.getListeDeComptes().get(0).getCompte();

                /**
                 * Getting sender amount in wallet and check whether it's enough
                 * for the transaction
                 */
                double senderAmount = compteSender.getMontant();
                double amountToSend = transaction.getMontant();

                if (senderAmount >= amountToSend) {

                    /**
                     * minus amount from sender and adds up to recipient account
                     */
                    double remainAmountSender = senderAmount - amountToSend;
                    double recipienNewAmount = compteRecipient.getMontant() + amountToSend;

                    compteSender.setMontant(remainAmountSender);
                    compteRecipient.setMontant(recipienNewAmount);

                    session.saveOrUpdate(compteSender);
                    session.saveOrUpdate(compteRecipient);
                    transaction.setFkStatutTransaction(3);
                    session.save(transaction);

                    suchOperation = 1;
                } else {
                    transaction.setFkStatutTransaction(2);
                    session.save(transaction);
                    suchOperation = 2;
                }

            } else {
                transaction.setFkStatutTransaction(2);
                session.save(transaction);
            }

            session.getSession().getTransaction().commit();
            /**
             * check what operation is done, to return result with callback
             */
            switch (suchOperation) {
                case 1:
                    transactionCallback.onSucceed(transaction);
                    break;
                case 2:
                    transactionCallback.onInsufficientAmount();
                    break;
                case 3:
                    transactionCallback.onFailed();
                    break;
            }

        } catch (Exception e) {
            Log.i(this, e.getMessage());
            transactionCallback.onFailed();
            e.printStackTrace();
            session.close();
            session.getSession().getTransaction().rollback();

        } finally {
            session.close();
        }
    }

}

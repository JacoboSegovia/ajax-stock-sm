/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rafaelaznar.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import net.rafaelaznar.bean.TipoproductoBean;
import net.rafaelaznar.data.MysqlData;
import net.rafaelaznar.helper.Conexion;
import net.rafaelaznar.helper.FilterBean;

public class TipoproductoDao_Mysql implements TipoproductoDao {

    private final MysqlData oMysql;
    private final Conexion.Tipo_conexion enumTipoConexion;

    public TipoproductoDao_Mysql(Conexion.Tipo_conexion tipoConexion) throws Exception {
        oMysql = new MysqlData();
        enumTipoConexion = tipoConexion;
    }

    @Override
    public int getPages(int intRegsPerPag, ArrayList<FilterBean> hmFilter, HashMap<String, String> hmOrder) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getPages("producto", intRegsPerPag, hmFilter, hmOrder);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("TipoproductoDao.getPages: Error: " + e.getMessage());
        }
    }

    @Override
    public int getCount(ArrayList<FilterBean> hmFilter) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getCount("producto", hmFilter);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("TipoproductoDao.getCount: Error: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<TipoproductoBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBean> hmFilter, HashMap<String, String> hmOrder) throws Exception {
        ArrayList<Integer> arrId;
        ArrayList<TipoproductoBean> arrTiporoducto = new ArrayList<>();
        try {
            oMysql.conexion(enumTipoConexion);
            arrId = oMysql.getPage("producto", intRegsPerPag, intPage, hmFilter, hmOrder);
            Iterator<Integer> iterador = arrId.listIterator();
            while (iterador.hasNext()) {
                TipoproductoBean oTipoproductoBean = new TipoproductoBean(iterador.next());
                arrTiporoducto.add(this.get(oTipoproductoBean));
            }
            oMysql.desconexion();
            return arrTiporoducto;
        } catch (Exception e) {
            throw new Exception("TipoproductoDao.getPage: Error: " + e.getMessage());
        }
    }

    @Override
    public TipoproductoBean get(TipoproductoBean oTipoproductoBean) throws Exception {
        if (oTipoproductoBean.getId() > 0) {
            try {
                oMysql.conexion(enumTipoConexion);
                if (!oMysql.existsOne("producto", oTipoproductoBean.getId())) {
                    oTipoproductoBean.setId(0);
                } else {
                    oTipoproductoBean.setDescripcion(oMysql.getOne("tipoproducto", "descripcion", oTipoproductoBean.getId()));
                }
            } catch (Exception e) {
                throw new Exception("TipoproductoDao.getTiporoducto: Error: " + e.getMessage());
            } finally {
                oMysql.desconexion();
            }
        } else {
            oTipoproductoBean.setId(0);
        }
        return oTipoproductoBean;
    }

    @Override
    public TipoproductoBean set(TipoproductoBean oTipoproductoBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.initTrans();
            if (oTipoproductoBean.getId() == 0) {
                oTipoproductoBean.setId(oMysql.insertOne("tipoproducto"));
            }
            oMysql.updateOne(oTipoproductoBean.getId(), "tipoproducto", "descripcion", oTipoproductoBean.getDescripcion());
            oMysql.commitTrans();
        } catch (Exception e) {
            oMysql.rollbackTrans();
            throw new Exception("TipoproductoDao.setTiporoducto: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
        return oTipoproductoBean;
    }

    @Override
    public void remove(TipoproductoBean oTipoproductoBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.removeOne(oTipoproductoBean.getId(), "producto");
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("TipoproductoDao.removeTiporoducto: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    @Override
    public ArrayList<String> getColumnsNames() throws Exception {
        ArrayList<String> alColumns=null;
        try {
            oMysql.conexion(enumTipoConexion);
            alColumns=oMysql.getColumnsName("producto", Conexion.getDatabaseName());
            oMysql.desconexion();
            
        } catch (Exception e) {
            throw new Exception("TipoproductoDao.removeTiporoducto: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
        return alColumns;
    }
}

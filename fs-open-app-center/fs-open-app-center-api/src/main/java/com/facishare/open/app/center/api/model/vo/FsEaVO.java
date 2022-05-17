package com.facishare.open.app.center.api.model.vo;

import java.io.Serializable;

/**
 * 纷享企业.
 * Created by zenglb on 2015/12/1.
 */
public class FsEaVO implements Serializable {
    private static final long serialVersionUID = -4233844475469791809L;

    /**
     * 企业账号
     */
    private String fsEa;

    /**
     * 企业名称
     */
    private String eaName;

    public String getFsEa() {
        return fsEa;
    }

    public void setFsEa(String fsEa) {
        this.fsEa = fsEa;
    }

    public String getEaName() {
        return eaName;
    }

    public void setEaName(String eaName) {
        this.eaName = eaName;
    }

    public FsEaVO() {
    }

    public FsEaVO(String fsEa, String eaName) {
        this.fsEa = fsEa;
        this.eaName = eaName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FsEaVO fsEA = (FsEaVO) o;
        if (fsEa != null ? !fsEa.equals(fsEA.fsEa) : fsEA.fsEa != null) return false;
        return !(eaName != null ? !eaName.equals(fsEA.eaName) : fsEA.eaName != null);
    }

    @Override
    public int hashCode() {
        int result = fsEa != null ? fsEa.hashCode() : 0;
        result = 31 * result + (eaName != null ? eaName.hashCode() : 0);
        return result;
    }
}

package br.gov.jfrj.siga.matrix.sinc;

// Generated 03/04/2012 11:48:10 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.gov.jfrj.siga.model.Assemelhavel;
import br.gov.jfrj.siga.sinc.lib.Desconsiderar;
import br.gov.jfrj.siga.sinc.lib.Sincronizavel;
import br.gov.jfrj.siga.sinc.lib.SincronizavelSuporte;

/**
 * Cards generated by hbm2java
 */
@Entity
@Table(name = "CARDS", schema = "MATRIX")
public class Cards implements java.io.Serializable, Sincronizavel {

    private static final String ACC_LVL_SEM_ACESSO = "NO ACC";
    private static final String ACC_LVL_SERVIDOR_SJRJ = "SJRJ-SERVI";
    private static final String ACC_LVL_ESTAGIARIO_SJRJ = "SJRJ-ESTAG";
    private static final String ACC_LVL_SERVIDOR_TRF2 = "TRF2-SERVI";

    private String staffNumber;

    @Desconsiderar
    private String cardNumber;

    private String name;

    @Desconsiderar
    private String pin;

    @Desconsiderar
    private String startingDate;

    @Desconsiderar
    private String expiryDate;

    private String accessLevel;

    @Desconsiderar
    private String floorAccess;

    @Desconsiderar
    private String lastLocation;

    @Desconsiderar
    private Character lastTransaction;

    @Desconsiderar
    private String photo;

    private String user1;
    private String user2;

    @Desconsiderar
    private String user3;

    private String user4;
    private String departCode;
    private String positionCode;

    @Desconsiderar
    private String cardType;

    @Desconsiderar
    private String misc;

    @Desconsiderar
    private String assignTo;

    @Desconsiderar
    private String clockAccess;

    @Desconsiderar
    private String displayName;

    public Cards() {
    }

    public Cards(String staffNumber, String cardNumber, String name,
            String accessLevel, String floorAccess, String departCode,
            String positionCode, String cardType) {
        this.staffNumber = staffNumber;
        this.cardNumber = cardNumber;
        this.name = name;
        this.accessLevel = accessLevel;
        this.floorAccess = floorAccess;
        this.departCode = departCode;
        this.positionCode = positionCode;
        this.cardType = cardType;
    }

    public Cards(String staffNumber, String cardNumber, String name,
            String pin, String startingDate, String expiryDate,
            String accessLevel, String floorAccess, String lastLocation,
            Character lastTransaction, String photo, String user1,
            String user2, String user3, String user4, String departCode,
            String positionCode, String cardType, String misc, String assignTo,
            String clockAccess, String displayName) {
        this.staffNumber = staffNumber;
        this.cardNumber = cardNumber;
        this.name = name;
        this.pin = pin;
        this.startingDate = startingDate;
        this.expiryDate = expiryDate;
        this.accessLevel = accessLevel;
        this.floorAccess = floorAccess;
        this.lastLocation = lastLocation;
        this.lastTransaction = lastTransaction;
        this.photo = photo;
        this.user1 = user1;
        this.user2 = user2;
        this.user3 = user3;
        this.user4 = user4;
        this.departCode = departCode;
        this.positionCode = positionCode;
        this.cardType = cardType;
        this.misc = misc;
        this.assignTo = assignTo;
        this.clockAccess = clockAccess;
        this.displayName = displayName;
    }

    @Id
    @Column(name = "STAFF_NUMBER", unique = true, nullable = false, length = 12)
    public String getStaffNumber() {
        return this.staffNumber;
    }

    public void setStaffNumber(String staffNumber) {
        this.staffNumber = staffNumber;
    }

    @Column(name = "CARD_NUMBER", nullable = false, length = 30)
    public String getCardNumber() {
        return this.cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Column(name = "NAME", nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "PIN", length = 20)
    public String getPin() {
        return this.pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    @Column(name = "STARTING_DATE", length = 8)
    public String getStartingDate() {
        return this.startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    @Column(name = "EXPIRY_DATE", length = 8)
    public String getExpiryDate() {
        return this.expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Column(name = "ACCESS_LEVEL", nullable = false, length = 30)
    public String getAccessLevel() {
        return this.accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    @Column(name = "FLOOR_ACCESS", nullable = false, length = 30)
    public String getFloorAccess() {
        return this.floorAccess;
    }

    public void setFloorAccess(String floorAccess) {
        this.floorAccess = floorAccess;
    }

    @Column(name = "LAST_LOCATION", length = 12)
    public String getLastLocation() {
        return this.lastLocation;
    }

    public void setLastLocation(String lastLocation) {
        this.lastLocation = lastLocation;
    }

    @Column(name = "LAST_TRANSACTION", length = 1)
    public Character getLastTransaction() {
        return this.lastTransaction;
    }

    public void setLastTransaction(Character lastTransaction) {
        this.lastTransaction = lastTransaction;
    }

    @Column(name = "PHOTO", length = 24)
    public String getPhoto() {
        return this.photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Column(name = "USER1", length = 40)
    public String getUser1() {
        return this.user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    @Column(name = "USER2", length = 40)
    public String getUser2() {
        return this.user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

    @Column(name = "USER3", length = 40)
    public String getUser3() {
        return this.user3;
    }

    public void setUser3(String user3) {
        this.user3 = user3;
    }

    @Column(name = "USER4", length = 40)
    public String getUser4() {
        return this.user4;
    }

    public void setUser4(String user4) {
        this.user4 = user4;
    }

    @Column(name = "DEPART_CODE", nullable = false, length = 4)
    public String getDepartCode() {
        return this.departCode;
    }

    public void setDepartCode(String departCode) {
        this.departCode = departCode;
    }

    @Column(name = "POSITION_CODE", nullable = false, length = 4)
    public String getPositionCode() {
        return this.positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    @Column(name = "CARD_TYPE", nullable = false, length = 1)
    public String getCardType() {
        return this.cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    @Column(name = "MISC", length = 20)
    public String getMisc() {
        return this.misc;
    }

    public void setMisc(String misc) {
        this.misc = misc;
    }

    @Column(name = "ASSIGN_TO", length = 12)
    public String getAssignTo() {
        return this.assignTo;
    }

    public void setAssignTo(String assignTo) {
        this.assignTo = assignTo;
    }

    @Column(name = "CLOCK_ACCESS", length = 30)
    public String getClockAccess() {
        return this.clockAccess;
    }

    public void setClockAccess(String clockAccess) {
        this.clockAccess = clockAccess;
    }

    @Column(name = "DISPLAY_NAME", length = 30)
    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public boolean semelhante(Assemelhavel obj, int profundidade) {
        // atende a pedido do Fabricio de sempre salvar o STARTING_DATE
        // return false;

        return SincronizavelSuporte.semelhante(this, obj, profundidade);
    }

    public boolean semelhante_real(Assemelhavel obj, int profundidade) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cards other = (Cards) obj;
        if (accessLevel == null) {
            if (other.accessLevel != null)
                return false;
        } else if (!accessLevel.equals(other.accessLevel)) {
            if (isAccessLevelPadrao(other.accessLevel)) {
                return false;
            }
        }

        if (departCode == null) {
            if (other.departCode != null)
                return false;
        } else if (!departCode.equals(other.departCode))
            return false;
        if (positionCode == null) {
            if (other.positionCode != null)
                return false;
        } else if (!positionCode.equals(other.positionCode))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (staffNumber == null) {
            if (other.staffNumber != null)
                return false;
        } else if (!staffNumber.equals(other.staffNumber))
            return false;
        if (cardNumber == null) {
            if (other.cardNumber != null)
                return false;
        } else if (!cardNumber.equals(other.cardNumber)) {
            if (cardNumber.equals(other.getId())) {
                return false;
            }
        }

        // cpf
        if (user1 == null) {
            if (other.user1 != null)
                return false;
        } else if (!user1.equals(other.user1))
            return false;
        // email
        if (user4 == null) {
            if (other.user4 != null)
                return false;
        } else if (!user4.equals(other.user4))
            return false;
        return true;
    }

    @Transient
    @Override
    public Long getId() {
        String idSemEspacos = staffNumber.trim();

        if (idSemEspacos.length() > 5) {
            String s = idSemEspacos.substring(idSemEspacos.length() - 5);
            if (s.startsWith("S"))
                s = s.substring(s.length() - 4);
            return Long.valueOf(s);
        } else
            return Long.valueOf(idSemEspacos.substring(2));
    }

    @Transient
    @Override
    public String getIdExterna() {
        return staffNumber.trim();
    }

    @Override
    public void setIdExterna(String idExterna) {
        staffNumber = idExterna;
    }

    @Transient
    @Override
    public Long getIdInicial() {
        return getId();
    }

    @Override
    public void setIdInicial(Long idInicial) {
        // staffNumber = idInicial.toString();
    }

    @Transient
    @Override
    public Date getDataInicio() {
        return null;
    }

    @Transient
    @Override
    public void setDataInicio(Date dataInicio) {

    }

    @Transient
    @Override
    public Date getDataFim() {
        return null;
    }

    @Transient
    @Override
    public void setDataFim(Date dataFim) {
    }

    @Transient
    @Override
    public String getLoteDeImportacao() {
        return null;
    }

    @Transient
    @Override
    public void setLoteDeImportacao(String loteDeImportacao) {
    }

    @Transient
    @Override
    public int getNivelDeDependencia() {
        return 0;
    }

    @Transient
    @Override
    public String getDescricaoExterna() {
        return accessLevel + ": " + staffNumber + " - " + name + " ("
                + departCode + ")";
    }

    public void copiarPropriedades(Cards outro) {
        this.staffNumber = outro.staffNumber;
        this.name = outro.name;
        this.accessLevel = outro.accessLevel;
        this.departCode = outro.departCode;
        this.positionCode = outro.positionCode;
        this.user1 = outro.user1;
        this.user2 = outro.user2;
        this.user4 = outro.user4;

        // Não atualizar este campo
        //
        // this.cardNumber = outro.cardNumber;

        // Campos que não são contemplados
        //
        // this.pin = outro.pin;
        // this.startingDate = outro.startingDate;
        // this.expiryDate = outro.expiryDate;
        // this.floorAccess = outro.floorAccess;
        // this.photo = outro.photo;
        // this.cardType = outro.cardType;
    }

    private boolean isAccessLevelPadrao(String accLvl) {
        if (accLvl != null && (accLvl.trim().equalsIgnoreCase(ACC_LVL_SERVIDOR_TRF2) ||
                accLvl.trim().equalsIgnoreCase(ACC_LVL_ESTAGIARIO_SJRJ) ||
                accLvl.trim().equalsIgnoreCase(ACC_LVL_SERVIDOR_SJRJ) ||
                accLvl.trim().equalsIgnoreCase(ACC_LVL_SEM_ACESSO))) {
            return true;
        }
        return false;
    }

}
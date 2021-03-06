package ext.appo.change.models;

@SuppressWarnings({"cast", "deprecation", "rawtypes", "unchecked"})
public abstract class _TransactionTask extends wt.fc.WTObject implements java.io.Externalizable {
   static final long serialVersionUID = 1;

   static final java.lang.String RESOURCE = "ext.appo.change.models.modelsResource";
   static final java.lang.String CLASSNAME = TransactionTask.class.getName();

   /**
    * 变更主题
    *
    * @see ext.appo.change.models.TransactionTask
    */
   public static final java.lang.String CHANGE_THEME = "changeTheme";
   static int CHANGE_THEME_UPPER_LIMIT = -1;
   java.lang.String changeTheme;
   /**
    * 变更主题
    *
    * @see ext.appo.change.models.TransactionTask
    */
   public java.lang.String getChangeTheme() {
      return changeTheme;
   }
   /**
    * 变更主题
    *
    * @see ext.appo.change.models.TransactionTask
    */
   public void setChangeTheme(java.lang.String changeTheme) throws wt.util.WTPropertyVetoException {
      changeThemeValidate(changeTheme);
      this.changeTheme = changeTheme;
   }
   void changeThemeValidate(java.lang.String changeTheme) throws wt.util.WTPropertyVetoException {
      if (CHANGE_THEME_UPPER_LIMIT < 1) {
         try { CHANGE_THEME_UPPER_LIMIT = (java.lang.Integer) wt.introspection.WTIntrospector.getClassInfo(CLASSNAME).getPropertyDescriptor("changeTheme").getValue(wt.introspection.WTIntrospector.UPPER_LIMIT); }
         catch (wt.introspection.WTIntrospectionException e) { CHANGE_THEME_UPPER_LIMIT = 200; }
      }
      if (changeTheme != null && !wt.fc.PersistenceHelper.checkStoredLength(changeTheme.toString(), CHANGE_THEME_UPPER_LIMIT, true))
         throw new wt.util.WTPropertyVetoException("wt.introspection.introspectionResource", wt.introspection.introspectionResource.UPPER_LIMIT,
               new java.lang.Object[] { new wt.introspection.PropertyDisplayName(CLASSNAME, "changeTheme"), java.lang.String.valueOf(java.lang.Math.min(CHANGE_THEME_UPPER_LIMIT, wt.fc.PersistenceHelper.DB_MAX_SQL_STRING_SIZE/wt.fc.PersistenceHelper.DB_MAX_BYTES_PER_CHAR)) },
               new java.beans.PropertyChangeEvent(this, "changeTheme", this.changeTheme, changeTheme));
   }

   /**
    * 变更任务描述
    *
    * @see ext.appo.change.models.TransactionTask
    */
   public static final java.lang.String CHANGE_DESCRIBE = "changeDescribe";
   static int CHANGE_DESCRIBE_UPPER_LIMIT = -1;
   java.lang.String changeDescribe;
   /**
    * 变更任务描述
    *
    * @see ext.appo.change.models.TransactionTask
    */
   public java.lang.String getChangeDescribe() {
      return changeDescribe;
   }
   /**
    * 变更任务描述
    *
    * @see ext.appo.change.models.TransactionTask
    */
   public void setChangeDescribe(java.lang.String changeDescribe) throws wt.util.WTPropertyVetoException {
      changeDescribeValidate(changeDescribe);
      this.changeDescribe = changeDescribe;
   }
   void changeDescribeValidate(java.lang.String changeDescribe) throws wt.util.WTPropertyVetoException {
      if (CHANGE_DESCRIBE_UPPER_LIMIT < 1) {
         try { CHANGE_DESCRIBE_UPPER_LIMIT = (java.lang.Integer) wt.introspection.WTIntrospector.getClassInfo(CLASSNAME).getPropertyDescriptor("changeDescribe").getValue(wt.introspection.WTIntrospector.UPPER_LIMIT); }
         catch (wt.introspection.WTIntrospectionException e) { CHANGE_DESCRIBE_UPPER_LIMIT = 200; }
      }
      if (changeDescribe != null && !wt.fc.PersistenceHelper.checkStoredLength(changeDescribe.toString(), CHANGE_DESCRIBE_UPPER_LIMIT, true))
         throw new wt.util.WTPropertyVetoException("wt.introspection.introspectionResource", wt.introspection.introspectionResource.UPPER_LIMIT,
               new java.lang.Object[] { new wt.introspection.PropertyDisplayName(CLASSNAME, "changeDescribe"), java.lang.String.valueOf(java.lang.Math.min(CHANGE_DESCRIBE_UPPER_LIMIT, wt.fc.PersistenceHelper.DB_MAX_SQL_STRING_SIZE/wt.fc.PersistenceHelper.DB_MAX_BYTES_PER_CHAR)) },
               new java.beans.PropertyChangeEvent(this, "changeDescribe", this.changeDescribe, changeDescribe));
   }

   /**
    * 责任人
    *
    * @see ext.appo.change.models.TransactionTask
    */
   public static final java.lang.String RESPONSIBLE = "responsible";
   static int RESPONSIBLE_UPPER_LIMIT = -1;
   java.lang.String responsible;
   /**
    * 责任人
    *
    * @see ext.appo.change.models.TransactionTask
    */
   public java.lang.String getResponsible() {
      return responsible;
   }
   /**
    * 责任人
    *
    * @see ext.appo.change.models.TransactionTask
    */
   public void setResponsible(java.lang.String responsible) throws wt.util.WTPropertyVetoException {
      responsibleValidate(responsible);
      this.responsible = responsible;
   }
   void responsibleValidate(java.lang.String responsible) throws wt.util.WTPropertyVetoException {
      if (RESPONSIBLE_UPPER_LIMIT < 1) {
         try { RESPONSIBLE_UPPER_LIMIT = (java.lang.Integer) wt.introspection.WTIntrospector.getClassInfo(CLASSNAME).getPropertyDescriptor("responsible").getValue(wt.introspection.WTIntrospector.UPPER_LIMIT); }
         catch (wt.introspection.WTIntrospectionException e) { RESPONSIBLE_UPPER_LIMIT = 200; }
      }
      if (responsible != null && !wt.fc.PersistenceHelper.checkStoredLength(responsible.toString(), RESPONSIBLE_UPPER_LIMIT, true))
         throw new wt.util.WTPropertyVetoException("wt.introspection.introspectionResource", wt.introspection.introspectionResource.UPPER_LIMIT,
               new java.lang.Object[] { new wt.introspection.PropertyDisplayName(CLASSNAME, "responsible"), java.lang.String.valueOf(java.lang.Math.min(RESPONSIBLE_UPPER_LIMIT, wt.fc.PersistenceHelper.DB_MAX_SQL_STRING_SIZE/wt.fc.PersistenceHelper.DB_MAX_BYTES_PER_CHAR)) },
               new java.beans.PropertyChangeEvent(this, "responsible", this.responsible, responsible));
   }

   /**
    * 期望完成日期
    *
    * @see ext.appo.change.models.TransactionTask
    */
   public static final java.lang.String NEED_DATE = "needDate";
   static int NEED_DATE_UPPER_LIMIT = -1;
   java.lang.String needDate;
   /**
    * 期望完成日期
    *
    * @see ext.appo.change.models.TransactionTask
    */
   public java.lang.String getNeedDate() {
      return needDate;
   }
   /**
    * 期望完成日期
    *
    * @see ext.appo.change.models.TransactionTask
    */
   public void setNeedDate(java.lang.String needDate) throws wt.util.WTPropertyVetoException {
      needDateValidate(needDate);
      this.needDate = needDate;
   }
   void needDateValidate(java.lang.String needDate) throws wt.util.WTPropertyVetoException {
      if (NEED_DATE_UPPER_LIMIT < 1) {
         try { NEED_DATE_UPPER_LIMIT = (java.lang.Integer) wt.introspection.WTIntrospector.getClassInfo(CLASSNAME).getPropertyDescriptor("needDate").getValue(wt.introspection.WTIntrospector.UPPER_LIMIT); }
         catch (wt.introspection.WTIntrospectionException e) { NEED_DATE_UPPER_LIMIT = 200; }
      }
      if (needDate != null && !wt.fc.PersistenceHelper.checkStoredLength(needDate.toString(), NEED_DATE_UPPER_LIMIT, true))
         throw new wt.util.WTPropertyVetoException("wt.introspection.introspectionResource", wt.introspection.introspectionResource.UPPER_LIMIT,
               new java.lang.Object[] { new wt.introspection.PropertyDisplayName(CLASSNAME, "needDate"), java.lang.String.valueOf(java.lang.Math.min(NEED_DATE_UPPER_LIMIT, wt.fc.PersistenceHelper.DB_MAX_SQL_STRING_SIZE/wt.fc.PersistenceHelper.DB_MAX_BYTES_PER_CHAR)) },
               new java.beans.PropertyChangeEvent(this, "needDate", this.needDate, needDate));
   }

   /**
    * ECA对象VID
    *
    * @see ext.appo.change.models.TransactionTask
    */
   public static final java.lang.String CHANGE_ACTIVITY2 = "changeActivity2";
   static int CHANGE_ACTIVITY2_UPPER_LIMIT = -1;
   java.lang.String changeActivity2;
   /**
    * ECA对象VID
    *
    * @see ext.appo.change.models.TransactionTask
    */
   public java.lang.String getChangeActivity2() {
      return changeActivity2;
   }
   /**
    * ECA对象VID
    *
    * @see ext.appo.change.models.TransactionTask
    */
   public void setChangeActivity2(java.lang.String changeActivity2) throws wt.util.WTPropertyVetoException {
      changeActivity2Validate(changeActivity2);
      this.changeActivity2 = changeActivity2;
   }
   void changeActivity2Validate(java.lang.String changeActivity2) throws wt.util.WTPropertyVetoException {
      if (CHANGE_ACTIVITY2_UPPER_LIMIT < 1) {
         try { CHANGE_ACTIVITY2_UPPER_LIMIT = (java.lang.Integer) wt.introspection.WTIntrospector.getClassInfo(CLASSNAME).getPropertyDescriptor("changeActivity2").getValue(wt.introspection.WTIntrospector.UPPER_LIMIT); }
         catch (wt.introspection.WTIntrospectionException e) { CHANGE_ACTIVITY2_UPPER_LIMIT = 200; }
      }
      if (changeActivity2 != null && !wt.fc.PersistenceHelper.checkStoredLength(changeActivity2.toString(), CHANGE_ACTIVITY2_UPPER_LIMIT, true))
         throw new wt.util.WTPropertyVetoException("wt.introspection.introspectionResource", wt.introspection.introspectionResource.UPPER_LIMIT,
               new java.lang.Object[] { new wt.introspection.PropertyDisplayName(CLASSNAME, "changeActivity2"), java.lang.String.valueOf(java.lang.Math.min(CHANGE_ACTIVITY2_UPPER_LIMIT, wt.fc.PersistenceHelper.DB_MAX_SQL_STRING_SIZE/wt.fc.PersistenceHelper.DB_MAX_BYTES_PER_CHAR)) },
               new java.beans.PropertyChangeEvent(this, "changeActivity2", this.changeActivity2, changeActivity2));
   }

   public java.lang.String getConceptualClassname() {
      return CLASSNAME;
   }

   public wt.introspection.ClassInfo getClassInfo() throws wt.introspection.WTIntrospectionException {
      return wt.introspection.WTIntrospector.getClassInfo(getConceptualClassname());
   }

   public java.lang.String getType() {
      try { return getClassInfo().getDisplayName(); }
      catch (wt.introspection.WTIntrospectionException wte) { return wt.util.WTStringUtilities.tail(getConceptualClassname(), '.'); }
   }

   public static final long EXTERNALIZATION_VERSION_UID = 5738293078621751499L;

   public void writeExternal(java.io.ObjectOutput output) throws java.io.IOException {
      output.writeLong( EXTERNALIZATION_VERSION_UID );

      super.writeExternal( output );

      output.writeObject( changeActivity2 );
      output.writeObject( changeDescribe );
      output.writeObject( changeTheme );
      output.writeObject( needDate );
      output.writeObject( responsible );
   }

   protected void super_writeExternal_TransactionTask(java.io.ObjectOutput output) throws java.io.IOException {
      super.writeExternal(output);
   }

   public void readExternal(java.io.ObjectInput input) throws java.io.IOException, java.lang.ClassNotFoundException {
      long readSerialVersionUID = input.readLong();
      readVersion( (ext.appo.change.models.TransactionTask) this, input, readSerialVersionUID, false, false );
   }
   protected void super_readExternal_TransactionTask(java.io.ObjectInput input) throws java.io.IOException, java.lang.ClassNotFoundException {
      super.readExternal(input);
   }

   public void writeExternal(wt.pds.PersistentStoreIfc output) throws java.sql.SQLException, wt.pom.DatastoreException {
      super.writeExternal( output );

      output.setString( "changeActivity2", changeActivity2 );
      output.setString( "changeDescribe", changeDescribe );
      output.setString( "changeTheme", changeTheme );
      output.setString( "needDate", needDate );
      output.setString( "responsible", responsible );
   }

   public void readExternal(wt.pds.PersistentRetrieveIfc input) throws java.sql.SQLException, wt.pom.DatastoreException {
      super.readExternal( input );

      changeActivity2 = input.getString( "changeActivity2" );
      changeDescribe = input.getString( "changeDescribe" );
      changeTheme = input.getString( "changeTheme" );
      needDate = input.getString( "needDate" );
      responsible = input.getString( "responsible" );
   }

   boolean readVersion5738293078621751499L( java.io.ObjectInput input, long readSerialVersionUID, boolean superDone ) throws java.io.IOException, java.lang.ClassNotFoundException {
      if ( !superDone )
         super.readExternal( input );

      changeActivity2 = (java.lang.String) input.readObject();
      changeDescribe = (java.lang.String) input.readObject();
      changeTheme = (java.lang.String) input.readObject();
      needDate = (java.lang.String) input.readObject();
      responsible = (java.lang.String) input.readObject();
      return true;
   }

   protected boolean readVersion( TransactionTask thisObject, java.io.ObjectInput input, long readSerialVersionUID, boolean passThrough, boolean superDone ) throws java.io.IOException, java.lang.ClassNotFoundException {
      boolean success = true;

      if ( readSerialVersionUID == EXTERNALIZATION_VERSION_UID )
         return readVersion5738293078621751499L( input, readSerialVersionUID, superDone );
      else
         success = readOldVersion( input, readSerialVersionUID, passThrough, superDone );

      if (input instanceof wt.pds.PDSObjectInput)
         wt.fc.EvolvableHelper.requestRewriteOfEvolvedBlobbedObject();

      return success;
   }
   protected boolean super_readVersion_TransactionTask( _TransactionTask thisObject, java.io.ObjectInput input, long readSerialVersionUID, boolean passThrough, boolean superDone ) throws java.io.IOException, java.lang.ClassNotFoundException {
      return super.readVersion(thisObject, input, readSerialVersionUID, passThrough, superDone);
   }

   boolean readOldVersion( java.io.ObjectInput input, long readSerialVersionUID, boolean passThrough, boolean superDone ) throws java.io.IOException, java.lang.ClassNotFoundException {
      throw new java.io.InvalidClassException(CLASSNAME, "Local class not compatible: stream classdesc externalizationVersionUID="+readSerialVersionUID+" local class externalizationVersionUID="+EXTERNALIZATION_VERSION_UID);
   }
}

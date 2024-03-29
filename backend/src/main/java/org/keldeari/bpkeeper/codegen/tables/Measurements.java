/*
 * This file is generated by jOOQ.
 */
package org.keldeari.bpkeeper.codegen.tables;


import java.time.OffsetDateTime;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function7;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row7;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;
import org.keldeari.bpkeeper.codegen.Keys;
import org.keldeari.bpkeeper.codegen.Public;
import org.keldeari.bpkeeper.codegen.tables.records.MeasurementsRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Measurements extends TableImpl<MeasurementsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.measurements</code>
     */
    public static final Measurements MEASUREMENTS = new Measurements();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<MeasurementsRecord> getRecordType() {
        return MeasurementsRecord.class;
    }

    /**
     * The column <code>public.measurements.entry_id</code>.
     */
    public final TableField<MeasurementsRecord, Long> ENTRY_ID = createField(DSL.name("entry_id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.measurements.user_id</code>.
     */
    public final TableField<MeasurementsRecord, Long> USER_ID = createField(DSL.name("user_id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.measurements.pressure_sys</code>.
     */
    public final TableField<MeasurementsRecord, Short> PRESSURE_SYS = createField(DSL.name("pressure_sys"), SQLDataType.SMALLINT.nullable(false), this, "");

    /**
     * The column <code>public.measurements.pressure_dia</code>.
     */
    public final TableField<MeasurementsRecord, Short> PRESSURE_DIA = createField(DSL.name("pressure_dia"), SQLDataType.SMALLINT.nullable(false), this, "");

    /**
     * The column <code>public.measurements.pulse</code>.
     */
    public final TableField<MeasurementsRecord, Short> PULSE = createField(DSL.name("pulse"), SQLDataType.SMALLINT, this, "");

    /**
     * The column <code>public.measurements.datetime</code>.
     */
    public final TableField<MeasurementsRecord, OffsetDateTime> DATETIME = createField(DSL.name("datetime"), SQLDataType.TIMESTAMPWITHTIMEZONE(6).nullable(false), this, "");

    /**
     * The column <code>public.measurements.note</code>.
     */
    public final TableField<MeasurementsRecord, String> NOTE = createField(DSL.name("note"), SQLDataType.CLOB, this, "");

    private Measurements(Name alias, Table<MeasurementsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Measurements(Name alias, Table<MeasurementsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.measurements</code> table reference
     */
    public Measurements(String alias) {
        this(DSL.name(alias), MEASUREMENTS);
    }

    /**
     * Create an aliased <code>public.measurements</code> table reference
     */
    public Measurements(Name alias) {
        this(alias, MEASUREMENTS);
    }

    /**
     * Create a <code>public.measurements</code> table reference
     */
    public Measurements() {
        this(DSL.name("measurements"), null);
    }

    public <O extends Record> Measurements(Table<O> child, ForeignKey<O, MeasurementsRecord> key) {
        super(child, key, MEASUREMENTS);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public Identity<MeasurementsRecord, Long> getIdentity() {
        return (Identity<MeasurementsRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<MeasurementsRecord> getPrimaryKey() {
        return Keys.MEASUREMENTS_PKEY;
    }

    @Override
    public Measurements as(String alias) {
        return new Measurements(DSL.name(alias), this);
    }

    @Override
    public Measurements as(Name alias) {
        return new Measurements(alias, this);
    }

    @Override
    public Measurements as(Table<?> alias) {
        return new Measurements(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Measurements rename(String name) {
        return new Measurements(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Measurements rename(Name name) {
        return new Measurements(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Measurements rename(Table<?> name) {
        return new Measurements(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row7 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row7<Long, Long, Short, Short, Short, OffsetDateTime, String> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function7<? super Long, ? super Long, ? super Short, ? super Short, ? super Short, ? super OffsetDateTime, ? super String, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function7<? super Long, ? super Long, ? super Short, ? super Short, ? super Short, ? super OffsetDateTime, ? super String, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}

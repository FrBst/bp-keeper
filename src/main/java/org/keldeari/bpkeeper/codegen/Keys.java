/*
 * This file is generated by jOOQ.
 */
package org.keldeari.bpkeeper.codegen;


import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;
import org.keldeari.bpkeeper.codegen.tables.Measurements;
import org.keldeari.bpkeeper.codegen.tables.records.MeasurementsRecord;


/**
 * A class modelling foreign key relationships and constraints of tables in
 * public.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<MeasurementsRecord> MEASUREMENTS_PKEY = Internal.createUniqueKey(Measurements.MEASUREMENTS, DSL.name("measurements_pkey"), new TableField[] { Measurements.MEASUREMENTS.ENTRY_ID }, true);
}
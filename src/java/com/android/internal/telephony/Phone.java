/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.internal.telephony;

// ???? 

/**
 * Internal interface used to control the phone; SDK developers cannot
 * obtain this interface.
 *
 * {@hide}
 *
 */
public interface Phone {

    /** used to enable additional debug messages */
    static final boolean DEBUG_PHONE = true;

    public enum DataActivityState {
        /**
         * The state of a data activity.
         * <ul>
         * <li>NONE = No traffic</li>
         * <li>DATAIN = Receiving IP ppp traffic</li>
         * <li>DATAOUT = Sending IP ppp traffic</li>
         * <li>DATAINANDOUT = Both receiving and sending IP ppp traffic</li>
         * <li>DORMANT = The data connection is still active,
                                     but physical link is down</li>
         * </ul>
         */
        NONE, DATAIN, DATAOUT, DATAINANDOUT, DORMANT;
    }

    enum SuppService {
      UNKNOWN, SWITCH, SEPARATE, TRANSFER, CONFERENCE, REJECT, HANGUP;
    }

    // "Features" accessible through the connectivity manager
    static final String FEATURE_ENABLE_MMS = "enableMMS";
    static final String FEATURE_ENABLE_SUPL = "enableSUPL";
    static final String FEATURE_ENABLE_DUN = "enableDUN";
    static final String FEATURE_ENABLE_HIPRI = "enableHIPRI";
    static final String FEATURE_ENABLE_DUN_ALWAYS = "enableDUNAlways";
    static final String FEATURE_ENABLE_FOTA = "enableFOTA";
    static final String FEATURE_ENABLE_IMS = "enableIMS";
    static final String FEATURE_ENABLE_CBS = "enableCBS";

    /**
     * Optional reasons for disconnect and connect
     */
    static final String REASON_ROAMING_ON = "roamingOn";
    static final String REASON_ROAMING_OFF = "roamingOff";
    static final String REASON_DATA_DISABLED = "dataDisabled";
    static final String REASON_DATA_ENABLED = "dataEnabled";
    static final String REASON_DATA_ATTACHED = "dataAttached";
    static final String REASON_DATA_DETACHED = "dataDetached";
    static final String REASON_CDMA_DATA_ATTACHED = "cdmaDataAttached";
    static final String REASON_CDMA_DATA_DETACHED = "cdmaDataDetached";
    static final String REASON_APN_CHANGED = "apnChanged";
    static final String REASON_APN_SWITCHED = "apnSwitched";
    static final String REASON_APN_FAILED = "apnFailed";
    static final String REASON_RESTORE_DEFAULT_APN = "restoreDefaultApn";
    static final String REASON_RADIO_TURNED_OFF = "radioTurnedOff";
    static final String REASON_PDP_RESET = "pdpReset";
    static final String REASON_VOICE_CALL_ENDED = "2GVoiceCallEnded";
    static final String REASON_VOICE_CALL_STARTED = "2GVoiceCallStarted";
    static final String REASON_PS_RESTRICT_ENABLED = "psRestrictEnabled";
    static final String REASON_PS_RESTRICT_DISABLED = "psRestrictDisabled";
    static final String REASON_SIM_LOADED = "simLoaded";
    static final String REASON_NW_TYPE_CHANGED = "nwTypeChanged";
    static final String REASON_DATA_DEPENDENCY_MET = "dependencyMet";
    static final String REASON_DATA_DEPENDENCY_UNMET = "dependencyUnmet";
    static final String REASON_LOST_DATA_CONNECTION = "lostDataConnection";
    static final String REASON_CONNECTED = "connected";
    static final String REASON_SINGLE_PDN_ARBITRATION = "SinglePdnArbitration";

    // Used for band mode selection methods
    static final int BM_UNSPECIFIED = 0; // selected by baseband automatically
    static final int BM_EURO_BAND   = 1; // GSM-900 / DCS-1800 / WCDMA-IMT-2000
    static final int BM_US_BAND     = 2; // GSM-850 / PCS-1900 / WCDMA-850 / WCDMA-PCS-1900
    static final int BM_JPN_BAND    = 3; // WCDMA-800 / WCDMA-IMT-2000
    static final int BM_AUS_BAND    = 4; // GSM-900 / DCS-1800 / WCDMA-850 / WCDMA-IMT-2000
    static final int BM_AUS2_BAND   = 5; // GSM-900 / DCS-1800 / WCDMA-850
    static final int BM_BOUNDARY    = 6; // upper band boundary

    // Used for preferred network type
    // Note NT_* substitute RILConstants.NETWORK_MODE_* above the Phone
    int NT_MODE_WCDMA_PREF   = RILConstants.NETWORK_MODE_WCDMA_PREF;
    int NT_MODE_GSM_ONLY     = RILConstants.NETWORK_MODE_GSM_ONLY;
    int NT_MODE_WCDMA_ONLY   = RILConstants.NETWORK_MODE_WCDMA_ONLY;
    int NT_MODE_GSM_UMTS     = RILConstants.NETWORK_MODE_GSM_UMTS;

    int NT_MODE_CDMA         = RILConstants.NETWORK_MODE_CDMA;

    int NT_MODE_CDMA_NO_EVDO = RILConstants.NETWORK_MODE_CDMA_NO_EVDO;
    int NT_MODE_EVDO_NO_CDMA = RILConstants.NETWORK_MODE_EVDO_NO_CDMA;
    int NT_MODE_GLOBAL       = RILConstants.NETWORK_MODE_GLOBAL;

    int NT_MODE_LTE_CDMA_AND_EVDO        = RILConstants.NETWORK_MODE_LTE_CDMA_EVDO;
    int NT_MODE_LTE_GSM_WCDMA            = RILConstants.NETWORK_MODE_LTE_GSM_WCDMA;
    int NT_MODE_LTE_CMDA_EVDO_GSM_WCDMA  = RILConstants.NETWORK_MODE_LTE_CMDA_EVDO_GSM_WCDMA;
    int NT_MODE_LTE_ONLY                 = RILConstants.NETWORK_MODE_LTE_ONLY;
    int NT_MODE_LTE_WCDMA                = RILConstants.NETWORK_MODE_LTE_WCDMA;
    int PREFERRED_NT_MODE                = RILConstants.PREFERRED_NETWORK_MODE;

    // Used for CDMA roaming mode
    static final int CDMA_RM_HOME        = 0;  // Home Networks only, as defined in PRL
    static final int CDMA_RM_AFFILIATED  = 1;  // Roaming an Affiliated networks, as defined in PRL
    static final int CDMA_RM_ANY         = 2;  // Roaming on Any Network, as defined in PRL

    // Used for CDMA subscription mode
    static final int CDMA_SUBSCRIPTION_UNKNOWN  =-1; // Unknown
    static final int CDMA_SUBSCRIPTION_RUIM_SIM = 0; // RUIM/SIM (default)
    static final int CDMA_SUBSCRIPTION_NV       = 1; // NV -> non-volatile memory

    static final int PREFERRED_CDMA_SUBSCRIPTION = CDMA_SUBSCRIPTION_NV;

    static final int TTY_MODE_OFF = 0;
    static final int TTY_MODE_FULL = 1;
    static final int TTY_MODE_HCO = 2;
    static final int TTY_MODE_VCO = 3;

     /**
     * CDMA OTA PROVISION STATUS, the same as RIL_CDMA_OTA_Status in ril.h
     */

    public static final int CDMA_OTA_PROVISION_STATUS_SPL_UNLOCKED = 0;
    public static final int CDMA_OTA_PROVISION_STATUS_SPC_RETRIES_EXCEEDED = 1;
    public static final int CDMA_OTA_PROVISION_STATUS_A_KEY_EXCHANGED = 2;
    public static final int CDMA_OTA_PROVISION_STATUS_SSD_UPDATED = 3;
    public static final int CDMA_OTA_PROVISION_STATUS_NAM_DOWNLOADED = 4;
    public static final int CDMA_OTA_PROVISION_STATUS_MDN_DOWNLOADED = 5;
    public static final int CDMA_OTA_PROVISION_STATUS_IMSI_DOWNLOADED = 6;
    public static final int CDMA_OTA_PROVISION_STATUS_PRL_DOWNLOADED = 7;
    public static final int CDMA_OTA_PROVISION_STATUS_COMMITTED = 8;
    public static final int CDMA_OTA_PROVISION_STATUS_OTAPA_STARTED = 9;
    public static final int CDMA_OTA_PROVISION_STATUS_OTAPA_STOPPED = 10;
    public static final int CDMA_OTA_PROVISION_STATUS_OTAPA_ABORTED = 11;
}

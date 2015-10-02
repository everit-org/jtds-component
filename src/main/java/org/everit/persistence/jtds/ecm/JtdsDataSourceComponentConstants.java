/*
 * Copyright (C) 2011 Everit Kft. (http://www.everit.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.everit.persistence.jtds.ecm;

import net.sourceforge.jtds.jdbc.DefaultProperties;
import net.sourceforge.jtds.jdbc.Driver;

/**
 * Configuration attributes and other constant values of JtdsDataSourceComponent.
 */
public final class JtdsDataSourceComponentConstants {

  public static final String ATTR_APPLICATION_NAME = "appName";

  public static final String ATTR_AUTO_COMMIT = "autoCommit";

  public static final String ATTR_BATCH_SIZE = "batchSize";

  public static final String ATTR_BIND_ADDRESS = "bindAddress";

  public static final String ATTR_BUFFER_DIR = "bufferDir";

  public static final String ATTR_BUFFER_MAX_MEMORY = "bufferMaxMemory";

  public static final String ATTR_BUFFER_MIN_PACKETS = "bufferMinPackets";

  public static final String ATTR_CACHE_META_DATA = "cacheMetaData";

  public static final String ATTR_CHARSET = "charset";

  public static final String ATTR_DATABASE_NAME = "databaseName";

  public static final String ATTR_DESCRIPTION = "description";

  public static final String ATTR_DOMAIN = "domain";

  public static final String ATTR_INSTANCE = "instance";

  public static final String ATTR_LANGUAGE = "language";

  public static final String ATTR_LAST_UPDATE_COUNT = "lastUpdateCount";

  public static final String ATTR_LOB_BUFFER = "lobBuffer";

  public static final String ATTR_LOG_FILE = "logFile";

  public static final String ATTR_LOGIN_TIMEOUT = "loginTimeout";

  public static final String ATTR_MAC_ADDRESS = "macAddress";

  public static final String ATTR_MAX_STATEMENTS = "maxStatements";

  public static final String ATTR_NAMED_PIPE = "namedPipe";

  public static final String ATTR_PACKET_SIZE = "packetSize";

  public static final String ATTR_PASSWORD = "password";

  public static final String ATTR_PORT_NUMBER = "port";

  public static final String ATTR_PREPARE_SQL = "prepareSQL";

  public static final String ATTR_PROCESS_ID = "processId";

  public static final String ATTR_PROGRAM_NAME = "progName";

  public static final String ATTR_SEND_STRING_PARAMETERS_AS_UNICODE =
      "sendStringParametersAsUnicode";

  public static final String ATTR_SERVER_NAME = "serverName";

  public static final String ATTR_SERVER_TYPE = "serverType";

  public static final String ATTR_SOCKET_KEEP_ALIVE = "socketKeepAlive";

  public static final String ATTR_SOCKET_TIMEOUT = "socketTimeout";

  public static final String ATTR_SSL = "ssl";

  public static final String ATTR_TCP_NO_DELAY = "tcpNoDelay";

  public static final String ATTR_TDS = "tds";

  public static final String ATTR_USE_CURSORS = "useCursors";

  public static final String ATTR_USE_JCIFS = "useJCIFS";

  public static final String ATTR_USE_KERBEROS = "useKerberos";

  public static final String ATTR_USE_LOBS = "useLOBs";

  public static final String ATTR_USE_NTLMV2 = "useNTLMv2";

  public static final String ATTR_USER = "user";

  public static final String ATTR_WSID = "wsid";

  public static final String ATTR_XA_EMULATION = "xaEmulation";

  public static final String DEFAULT_APP_NAME = DefaultProperties.APP_NAME;

  public static final boolean DEFAULT_AUTO_COMMIT = true;

  public static final int DEFAULT_BUFFER_MAX_MEMORY = 1024;

  public static final int DEFAULT_BUFFER_MIN_PACKETS = 8;

  public static final boolean DEFAULT_CACHE_META_DATA = false;

  public static final boolean DEFAULT_LAST_UPDATE_COUNT = true;

  public static final long DEFAULT_LOB_BUFFER = 32 * 1024;

  public static final String DEFAULT_MAC_ADDRESS = DefaultProperties.MAC_ADDRESS;

  public static final int DEFAULT_MAX_STATEMENTS = 500;

  public static final boolean DEFAULT_NAMED_PIPE = false;

  public static final String DEFAULT_PROCESS_ID = DefaultProperties.PROCESS_ID;

  public static final String DEFAULT_PROGRAM_NAME = DefaultProperties.PROG_NAME;

  public static final boolean DEFAULT_SEND_STRING_PARAMETERS_AS_UNICODE = true;

  public static final boolean DEFAULT_SOCKET_KEEP_ALIVE = false;

  public static final int DEFAULT_SOCKET_TIMEOUT = 0;

  public static final boolean DEFAULT_TCP_NO_DELAY = true;

  public static final boolean DEFAULT_USE_CURSORS = false;

  public static final boolean DEFAULT_USE_JCIFS = false;

  public static final boolean DEFAULT_USE_KERBEROS = false;

  public static final boolean DEFAULT_USE_LOBS = true;

  public static final boolean DEFAULT_USE_NTLMV2 = false;

  public static final boolean DEFAULT_XA_EMULATION = true;

  public static final int OPTION_VALUE_SERVERTYPE_SQLSERVER = Driver.SQLSERVER;

  public static final int OPTION_VALUE_SERVERTYPE_SYBASE = Driver.SYBASE;

  public static final String OPTION_VALUE_SSL_AUTHENTICATE = "authenticate";

  public static final String OPTION_VALUE_SSL_OFF = "off";

  public static final String OPTION_VALUE_SSL_REQUEST = "request";

  public static final String OPTION_VALUE_SSL_REQUIRE = "require";

  public static final String SERVICE_FACTORY_PID =
      "org.everit.persistence.jtds.ecm.JtdsDataSourceComponent";

  private JtdsDataSourceComponentConstants() {
  }
}

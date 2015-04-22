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
package org.everit.osgi.jtds.component.internal;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Map;

import javax.sql.ConnectionPoolDataSource;
import javax.sql.DataSource;
import javax.sql.XADataSource;

import net.sourceforge.jtds.jdbcx.JtdsDataSource;

import org.everit.osgi.ecm.annotation.Activate;
import org.everit.osgi.ecm.annotation.AttributeOrder;
import org.everit.osgi.ecm.annotation.Component;
import org.everit.osgi.ecm.annotation.ConfigurationPolicy;
import org.everit.osgi.ecm.annotation.Deactivate;
import org.everit.osgi.ecm.annotation.attribute.BooleanAttribute;
import org.everit.osgi.ecm.annotation.attribute.IntegerAttribute;
import org.everit.osgi.ecm.annotation.attribute.IntegerAttributeOption;
import org.everit.osgi.ecm.annotation.attribute.LongAttribute;
import org.everit.osgi.ecm.annotation.attribute.StringAttribute;
import org.everit.osgi.ecm.annotation.attribute.StringAttributeOption;
import org.everit.osgi.ecm.annotation.attribute.StringAttributes;
import org.everit.osgi.ecm.extender.ECMExtenderConstants;
import org.everit.osgi.jtds.component.JtdsDataSourceComponentConstants;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceRegistration;

import aQute.bnd.annotation.headers.ProvideCapability;

/**
 * Configurable component that registers {@link JtdsDataSource} as an OSGi service.
 */
@Component(componentId = JtdsDataSourceComponentConstants.FACTORY_PID,
    configurationPolicy = ConfigurationPolicy.FACTORY)
@ProvideCapability(ns = ECMExtenderConstants.CAPABILITY_NS_COMPONENT,
    value = ECMExtenderConstants.CAPABILITY_ATTR_CLASS + "=${@class}")
@StringAttributes(@StringAttribute(attributeId = Constants.SERVICE_DESCRIPTION, optional = true))
@AttributeOrder({ JtdsDataSourceComponentConstants.PROP_SERVERTYPE,
    JtdsDataSourceComponentConstants.PROP_SERVERNAME,
    JtdsDataSourceComponentConstants.PROP_PORTNUMBER,
    JtdsDataSourceComponentConstants.PROP_USER,
    JtdsDataSourceComponentConstants.PROP_PASSWORD,
    JtdsDataSourceComponentConstants.PROP_DATABASENAME,
    JtdsDataSourceComponentConstants.PROP_AUTOCOMMIT,
    JtdsDataSourceComponentConstants.PROP_SSL,
    JtdsDataSourceComponentConstants.PROP_APPNAME, JtdsDataSourceComponentConstants.PROP_BATCHSIZE,
    JtdsDataSourceComponentConstants.PROP_BINDADDRESS,
    JtdsDataSourceComponentConstants.PROP_BUFFERDIR,
    JtdsDataSourceComponentConstants.PROP_BUFFERMAXMEMORY,
    JtdsDataSourceComponentConstants.PROP_BUFFERMINPACKETS,
    JtdsDataSourceComponentConstants.PROP_CHARSET,
    JtdsDataSourceComponentConstants.PROP_CACHEMETA,
    JtdsDataSourceComponentConstants.PROP_DESCRIPTION,
    JtdsDataSourceComponentConstants.PROP_DOMAIN,
    JtdsDataSourceComponentConstants.PROP_INSTANCE,
    JtdsDataSourceComponentConstants.PROP_LANGUAGE,
    JtdsDataSourceComponentConstants.PROP_LASTUPDATECOUNT,
    JtdsDataSourceComponentConstants.PROP_LOBBUFFER,
    JtdsDataSourceComponentConstants.PROP_LOGFILE,
    JtdsDataSourceComponentConstants.PROP_LOGINTIMEOUT,
    JtdsDataSourceComponentConstants.PROP_MACADDRESS,
    JtdsDataSourceComponentConstants.PROP_MAXSTATEMENTS,
    JtdsDataSourceComponentConstants.PROP_NAMEDPIPE,
    JtdsDataSourceComponentConstants.PROP_PACKETSIZE,
    JtdsDataSourceComponentConstants.PROP_PREPARESQL,
    JtdsDataSourceComponentConstants.PROP_PROGNAME,
    JtdsDataSourceComponentConstants.PROP_SENDSTRINGPARAMETERSASUNICODE,
    JtdsDataSourceComponentConstants.PROP_SOKEEPALIVE,
    JtdsDataSourceComponentConstants.PROP_SOTIMEOUT,
    JtdsDataSourceComponentConstants.PROP_TCPNODELAY,
    JtdsDataSourceComponentConstants.PROP_TDS,
    JtdsDataSourceComponentConstants.PROP_USECURSORS,
    JtdsDataSourceComponentConstants.PROP_USEJCIFS,
    JtdsDataSourceComponentConstants.PROP_USEKERBEROS,
    JtdsDataSourceComponentConstants.PROP_USELOBS,
    JtdsDataSourceComponentConstants.PROP_USENTLMV2,
    JtdsDataSourceComponentConstants.PROP_WSID,
    Constants.SERVICE_DESCRIPTION })
public class JtdsDataSourceComponent {

  private String appName;

  private boolean autoCommit;

  private Integer batchSize;

  private String bindAddress;

  private String bufferDir;

  private int bufferMaxMemory;

  private int bufferMinPackets;

  private boolean cacheMetaData;

  private String charset;

  private String databaseName;

  private String description;

  private String domain;

  private String instance;

  private String language;

  private boolean lastUpdateCount;

  private long lobBuffer;

  private String logFile;

  private Integer loginTimeout;

  private String macAddress;

  private int maxStatements;

  private boolean namedPipe;

  private Integer packetSize;

  private String password;

  private Integer portNumber;

  private Integer prepareSql;

  private String processId;

  private String progName;

  private boolean sendStringParametersAsUnicode;

  private String serverName;

  private int serverType;

  private ServiceRegistration<?> serviceRegistration;

  private boolean socketKeepAlive;

  private int socketTimeout;

  private String ssl;

  private boolean tcpNoDelay;

  private String tds;

  private boolean useCursors;

  private boolean useJCIFS;

  private boolean useKerberos;

  private boolean useLOBs;

  private boolean useNTLMv2;

  private String user;

  private String wsid;

  private boolean xaEmulation;

  /**
   * Instantiates a {@link JtdsDataSource} and registers it as an OSGi service.
   *
   * @param bundleContext
   *          The context of the bundle.
   * @param properties
   *          The properties of the component.
   */
  @Activate
  public void activate(final BundleContext bundleContext, final Map<String, Object> properties) {
    JtdsDataSource jtdsDataSource = new JtdsDataSource();

    updatePropertiesAToD(jtdsDataSource);
    updatePropertiesIToN(jtdsDataSource);
    updatePropertiesPToX(jtdsDataSource);

    Dictionary<String, Object> serviceProperties = new Hashtable<String, Object>(properties);
    serviceRegistration = bundleContext.registerService(
        new String[] { DataSource.class.getName(), XADataSource.class.getName(),
            ConnectionPoolDataSource.class.getName(), JtdsDataSource.class.getName() },
        jtdsDataSource, serviceProperties);
  }

  /**
   * Unregisters the {@link JtdsDataSource} OSGi service.
   */
  @Deactivate
  public void deactivate() {
    if (serviceRegistration != null) {
      serviceRegistration.unregister();
    }
  }

  @StringAttribute(attributeId = JtdsDataSourceComponentConstants.PROP_APPNAME,
      defaultValue = JtdsDataSourceComponentConstants.DEFAULT_APPNAME)
  public void setAppName(final String appName) {
    this.appName = appName;
  }

  @BooleanAttribute(attributeId = JtdsDataSourceComponentConstants.PROP_AUTOCOMMIT,
      defaultValue = JtdsDataSourceComponentConstants.DEFAULT_AUTOCOMMIT)
  public void setAutoCommit(final boolean autoCommit) {
    this.autoCommit = autoCommit;
  }

  @IntegerAttribute(attributeId = JtdsDataSourceComponentConstants.PROP_BATCHSIZE, optional = true)
  public void setBatchSize(final Integer batchSize) {
    this.batchSize = batchSize;
  }

  @StringAttribute(attributeId = JtdsDataSourceComponentConstants.PROP_BINDADDRESS, optional = true)
  public void setBindAddress(final String bindAddress) {
    this.bindAddress = bindAddress;
  }

  @StringAttribute(attributeId = JtdsDataSourceComponentConstants.PROP_BUFFERDIR, optional = true)
  public void setBufferDir(final String bufferDir) {
    this.bufferDir = bufferDir;
  }

  @IntegerAttribute(attributeId = JtdsDataSourceComponentConstants.PROP_BUFFERMAXMEMORY,
      defaultValue = JtdsDataSourceComponentConstants.DEFAULT_BUFFERMAXMEMORY)
  public void setBufferMaxMemory(final int bufferMaxMemory) {
    this.bufferMaxMemory = bufferMaxMemory;
  }

  @IntegerAttribute(attributeId = JtdsDataSourceComponentConstants.PROP_BUFFERMINPACKETS,
      defaultValue = JtdsDataSourceComponentConstants.DEFAULT_BUFFERMINPACKETS)
  public void setBufferMinPackets(final int bufferMinPackets) {
    this.bufferMinPackets = bufferMinPackets;
  }

  @BooleanAttribute(attributeId = JtdsDataSourceComponentConstants.PROP_CACHEMETA,
      defaultValue = JtdsDataSourceComponentConstants.DEFAULT_CACHEMETA)
  public void setCacheMetaData(final boolean cacheMetaData) {
    this.cacheMetaData = cacheMetaData;
  }

  @StringAttribute(attributeId = JtdsDataSourceComponentConstants.PROP_CHARSET, optional = true)
  public void setCharset(final String charset) {
    this.charset = charset;
  }

  @StringAttribute(attributeId = JtdsDataSourceComponentConstants.PROP_DATABASENAME,
      optional = true)
  public void setDatabaseName(final String databaseName) {
    this.databaseName = databaseName;
  }

  @StringAttribute(attributeId = JtdsDataSourceComponentConstants.PROP_DESCRIPTION, optional = true)
  public void setDescription(final String description) {
    this.description = description;
  }

  @StringAttribute(attributeId = JtdsDataSourceComponentConstants.PROP_DOMAIN, optional = true)
  public void setDomain(final String domain) {
    this.domain = domain;
  }

  @StringAttribute(attributeId = JtdsDataSourceComponentConstants.PROP_INSTANCE, optional = true)
  public void setInstance(final String instance) {
    this.instance = instance;
  }

  @StringAttribute(attributeId = JtdsDataSourceComponentConstants.PROP_LANGUAGE, optional = true)
  public void setLanguage(final String language) {
    this.language = language;
  }

  @BooleanAttribute(attributeId = JtdsDataSourceComponentConstants.PROP_LASTUPDATECOUNT,
      defaultValue = JtdsDataSourceComponentConstants.DEFAULT_LASTUPDATECOUNT)
  public void setLastUpdateCount(final boolean lastUpdateCount) {
    this.lastUpdateCount = lastUpdateCount;
  }

  @LongAttribute(attributeId = JtdsDataSourceComponentConstants.PROP_LOBBUFFER,
      defaultValue = JtdsDataSourceComponentConstants.DEFAULT_LOBBUFFER)
  public void setLobBuffer(final long lobBuffer) {
    this.lobBuffer = lobBuffer;
  }

  @StringAttribute(attributeId = JtdsDataSourceComponentConstants.PROP_LOGFILE, optional = true)
  public void setLogFile(final String logFile) {
    this.logFile = logFile;
  }

  @IntegerAttribute(attributeId = JtdsDataSourceComponentConstants.PROP_LOGINTIMEOUT,
      optional = true)
  public void setLoginTimeout(final Integer loginTimeout) {
    this.loginTimeout = loginTimeout;
  }

  @StringAttribute(attributeId = JtdsDataSourceComponentConstants.PROP_MACADDRESS,
      defaultValue = JtdsDataSourceComponentConstants.DEFAULT_MACADDRESS)
  public void setMacAddress(final String macAddress) {
    this.macAddress = macAddress;
  }

  @IntegerAttribute(attributeId = JtdsDataSourceComponentConstants.PROP_MAXSTATEMENTS,
      defaultValue = JtdsDataSourceComponentConstants.DEFAULT_MAXSTATEMENTS)
  public void setMaxStatements(final int maxStatements) {
    this.maxStatements = maxStatements;
  }

  @BooleanAttribute(attributeId = JtdsDataSourceComponentConstants.PROP_NAMEDPIPE,
      defaultValue = JtdsDataSourceComponentConstants.DEFAULT_NAMEDPIPE)
  public void setNamedPipe(final boolean namedPipe) {
    this.namedPipe = namedPipe;
  }

  @IntegerAttribute(attributeId = JtdsDataSourceComponentConstants.PROP_PACKETSIZE, optional = true)
  public void setPacketSize(final Integer packetSize) {
    this.packetSize = packetSize;
  }

  @StringAttribute(attributeId = JtdsDataSourceComponentConstants.PROP_PASSWORD)
  public void setPassword(final String password) {
    this.password = password;
  }

  @IntegerAttribute(attributeId = JtdsDataSourceComponentConstants.PROP_PORTNUMBER, optional = true)
  public void setPortNumber(final Integer portNumber) {
    this.portNumber = portNumber;
  }

  @IntegerAttribute(attributeId = JtdsDataSourceComponentConstants.PROP_PREPARESQL, optional = true)
  public void setPrepareSql(final Integer prepareSql) {
    this.prepareSql = prepareSql;
  }

  @StringAttribute(attributeId = JtdsDataSourceComponentConstants.PROP_PROCESSID,
      defaultValue = JtdsDataSourceComponentConstants.DEFAULT_PROCESSID)
  public void setProcessId(final String processId) {
    this.processId = processId;
  }

  @StringAttribute(attributeId = JtdsDataSourceComponentConstants.PROP_PROGNAME,
      defaultValue = JtdsDataSourceComponentConstants.DEFAULT_PROGNAME)
  public void setProgName(final String progName) {
    this.progName = progName;
  }

  @BooleanAttribute(
      attributeId = JtdsDataSourceComponentConstants.PROP_SENDSTRINGPARAMETERSASUNICODE,
      defaultValue = JtdsDataSourceComponentConstants.DEFAULT_SENDSTRINGPARAMETERSASUNICODE)
  public void setSendStringParametersAsUnicode(final boolean sendStringParametersAsUnicode) {
    this.sendStringParametersAsUnicode = sendStringParametersAsUnicode;
  }

  @StringAttribute(attributeId = JtdsDataSourceComponentConstants.PROP_SERVERNAME)
  public void setServerName(final String serverName) {
    this.serverName = serverName;
  }

  @IntegerAttribute(attributeId = JtdsDataSourceComponentConstants.PROP_SERVERTYPE,
      options = { @IntegerAttributeOption(
          value = JtdsDataSourceComponentConstants.OPTION_VALUE_SERVERTYPE_SQLSERVER,
          label = "%serverType.options.sqlserver.label"),
          @IntegerAttributeOption(
              value = JtdsDataSourceComponentConstants.OPTION_VALUE_SERVERTYPE_SYBASE,
              label = "%serverType.options.sybase.label") },
      defaultValue = JtdsDataSourceComponentConstants.OPTION_VALUE_SERVERTYPE_SQLSERVER)
  public void setServerType(final int serverType) {
    this.serverType = serverType;
  }

  @BooleanAttribute(attributeId = JtdsDataSourceComponentConstants.PROP_SOKEEPALIVE,
      defaultValue = JtdsDataSourceComponentConstants.DEFAULT_SOKEEPALIVE)
  public void setSocketKeepAlive(final boolean socketKeepAlive) {
    this.socketKeepAlive = socketKeepAlive;
  }

  @IntegerAttribute(attributeId = JtdsDataSourceComponentConstants.PROP_SOTIMEOUT,
      defaultValue = JtdsDataSourceComponentConstants.DEFAULT_SOTIMEOUT)
  public void setSocketTimeout(final int socketTimeout) {
    this.socketTimeout = socketTimeout;
  }

  @StringAttribute(attributeId = JtdsDataSourceComponentConstants.PROP_SSL,
      options = { @StringAttributeOption(label = "%ssl.options.off.label",
          value = JtdsDataSourceComponentConstants.OPTION_VALUE_SSL_OFF),
          @StringAttributeOption(label = "%ssl.options.request.label",
              value = JtdsDataSourceComponentConstants.OPTION_VALUE_SSL_REQUEST),
          @StringAttributeOption(label = "%ssl.options.require.label",
              value = JtdsDataSourceComponentConstants.OPTION_VALUE_SSL_REQUIRE),
          @StringAttributeOption(label = "%ssl.options.authenticate.label",
              value = JtdsDataSourceComponentConstants.OPTION_VALUE_SSL_AUTHENTICATE) },
      defaultValue = JtdsDataSourceComponentConstants.OPTION_VALUE_SSL_OFF)
  public void setSsl(final String ssl) {
    this.ssl = ssl;
  }

  @BooleanAttribute(attributeId = JtdsDataSourceComponentConstants.PROP_TCPNODELAY,
      defaultValue = JtdsDataSourceComponentConstants.DEFAULT_TCPNODELAY)
  public void setTcpNoDelay(final boolean tcpNoDelay) {
    this.tcpNoDelay = tcpNoDelay;
  }

  @StringAttribute(attributeId = JtdsDataSourceComponentConstants.PROP_TDS, optional = true)
  public void setTds(final String tds) {
    this.tds = tds;
  }

  @BooleanAttribute(attributeId = JtdsDataSourceComponentConstants.PROP_USECURSORS,
      defaultValue = JtdsDataSourceComponentConstants.DEFAULT_USECURSORS)
  public void setUseCursors(final boolean useCursors) {
    this.useCursors = useCursors;
  }

  @BooleanAttribute(attributeId = JtdsDataSourceComponentConstants.PROP_USEJCIFS,
      defaultValue = JtdsDataSourceComponentConstants.DEFAULT_USEJCIFS)
  public void setUseJCIFS(final boolean useJCIFS) {
    this.useJCIFS = useJCIFS;
  }

  @BooleanAttribute(attributeId = JtdsDataSourceComponentConstants.PROP_USEKERBEROS,
      defaultValue = JtdsDataSourceComponentConstants.DEFAULT_USEKERBEROS)
  public void setUseKerberos(final boolean useKerberos) {
    this.useKerberos = useKerberos;
  }

  @BooleanAttribute(attributeId = JtdsDataSourceComponentConstants.PROP_USELOBS,
      defaultValue = JtdsDataSourceComponentConstants.DEFAULT_USELOBS)
  public void setUseLOBs(final boolean useLOBs) {
    this.useLOBs = useLOBs;
  }

  @BooleanAttribute(attributeId = JtdsDataSourceComponentConstants.PROP_USENTLMV2,
      defaultValue = JtdsDataSourceComponentConstants.DEFAULT_USENTLMV2)
  public void setUseNTLMv2(final boolean useNTLMv2) {
    this.useNTLMv2 = useNTLMv2;
  }

  @StringAttribute(attributeId = JtdsDataSourceComponentConstants.PROP_USER)
  public void setUser(final String user) {
    this.user = user;
  }

  @StringAttribute(attributeId = JtdsDataSourceComponentConstants.PROP_WSID, optional = true)
  public void setWsid(final String wsid) {
    this.wsid = wsid;
  }

  @BooleanAttribute(attributeId = JtdsDataSourceComponentConstants.PROP_XAEMULATION,
      defaultValue = JtdsDataSourceComponentConstants.DEFAULT_XAEMULATION)
  public void setXaEmulation(final boolean xaEmulation) {
    this.xaEmulation = xaEmulation;
  }

  private void updatePropertiesAToD(final JtdsDataSource jtdsDataSource) {
    jtdsDataSource.setAppName(appName);
    jtdsDataSource.setAutoCommit(autoCommit);
    if (batchSize != null) {
      jtdsDataSource.setBatchSize(batchSize);
    }
    if (bindAddress != null) {
      jtdsDataSource.setBindAddress(bindAddress);
    }
    if (bufferDir != null) {
      jtdsDataSource.setBufferDir(bufferDir);
    }
    jtdsDataSource.setBufferMaxMemory(bufferMaxMemory);
    jtdsDataSource.setBufferMinPackets(bufferMinPackets);
    jtdsDataSource.setCacheMetaData(cacheMetaData);
    if (charset != null) {
      jtdsDataSource.setCharset(charset);
    }
    if (databaseName != null) {
      jtdsDataSource.setDatabaseName(databaseName);
    }
    if (description != null) {
      jtdsDataSource.setDescription(description);
    }
    if (domain != null) {
      jtdsDataSource.setDomain(domain);
    }
  }

  private void updatePropertiesIToN(final JtdsDataSource jtdsDataSource) {
    if (instance != null) {
      jtdsDataSource.setInstance(instance);
    }
    if (language != null) {
      jtdsDataSource.setLanguage(language);
    }
    jtdsDataSource.setLastUpdateCount(lastUpdateCount);
    jtdsDataSource.setLobBuffer(lobBuffer);
    if (logFile != null) {
      jtdsDataSource.setLogFile(logFile);
    }
    if (loginTimeout != null) {
      jtdsDataSource.setLoginTimeout(loginTimeout);
    }
    jtdsDataSource.setMacAddress(macAddress);
    jtdsDataSource.setMaxStatements(maxStatements);
    jtdsDataSource.setNamedPipe(namedPipe);
  }

  private void updatePropertiesPToX(final JtdsDataSource jtdsDataSource) {
    if (packetSize != null) {
      jtdsDataSource.setPacketSize(packetSize);
    }
    jtdsDataSource.setPassword(password);
    if (portNumber != null) {
      jtdsDataSource.setPortNumber(portNumber);
    }
    if (prepareSql != null) {
      jtdsDataSource.setPrepareSql(prepareSql);
    }
    jtdsDataSource.setProcessId(processId);
    jtdsDataSource.setProgName(progName);
    jtdsDataSource.setSendStringParametersAsUnicode(sendStringParametersAsUnicode);
    jtdsDataSource.setServerName(serverName);
    jtdsDataSource.setServerType(serverType);
    jtdsDataSource.setSocketKeepAlive(socketKeepAlive);
    jtdsDataSource.setSocketTimeout(socketTimeout);
    jtdsDataSource.setSsl(ssl);
    jtdsDataSource.setTcpNoDelay(tcpNoDelay);
    if (tds != null) {
      jtdsDataSource.setTds(tds);
    }
    jtdsDataSource.setUseCursors(useCursors);
    jtdsDataSource.setUseJCIFS(useJCIFS);
    jtdsDataSource.setUseKerberos(useKerberos);
    jtdsDataSource.setUseLOBs(useLOBs);
    jtdsDataSource.setUseNTLMV2(useNTLMv2);
    jtdsDataSource.setUser(user);
    if (wsid != null) {
      jtdsDataSource.setWsid(wsid);
    }
    jtdsDataSource.setXaEmulation(xaEmulation);
  }
}

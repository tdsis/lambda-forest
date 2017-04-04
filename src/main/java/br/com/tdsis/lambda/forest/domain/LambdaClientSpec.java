package br.com.tdsis.lambda.forest.domain;

import com.amazonaws.services.lambda.runtime.Client;

import br.com.tdsis.lambda.forest.util.LambdaRunner;

/**
 * The LambdaClientSpec class
 * <p>
 * This class represents a {@code Client} 
 * during the lambda handler execution. 
 * <p> 
 * It should be used only for local tests functionality
 * using {@code LambdaRunner}
 * 
 * @author nmelo
 * @version 1.0.0
 * @since 1.0.0
 * @see {@link LambdaRunner}
 */
public class LambdaClientSpec implements Client {

    private String installationId;
    private String appTitle;
    private String appVersionName;
    private String appVersionCode;
    private String appPackageName;
    
    /**
     * Returns the installation id
     * 
     * @see {@link Client#getInstallationId()}
     */
    public String getInstallationId() {
        return installationId;
    }

    /**
     * Returns the app title
     * 
     * @see {@link Client#getAppTitle()}
     */
    public String getAppTitle() {
        return appTitle;
    }

    /**
     * Returns the app version name
     * 
     * @see {@link Client#getAppVersionName()}
     */
    public String getAppVersionName() {
        return appVersionName;
    }

    /**
     * Returns the app version code
     * 
     * @see {@link Client#getAppVersionCode()}
     */
    public String getAppVersionCode() {
        return appVersionCode;
    }

    /**
     * Returns the app package name
     * 
     * @see {@link Client#getAppPackageName()}
     */
    public String getAppPackageName() {
        return appPackageName;
    }
    
}

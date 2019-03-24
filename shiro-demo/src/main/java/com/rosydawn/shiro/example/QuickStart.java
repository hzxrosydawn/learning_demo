package com.rosydawn.shiro.example;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 快速上手实例
 *
 * @author Vincent
 **/
public class QuickStart {
    private static final Logger log = LoggerFactory.getLogger(QuickStart.class);

    public static void main(String[] args) {
        // The easiest way to create a Shiro SecurityManager with configured
        // realms, users, roles and permissions is to use the simple INI config.
        // We'll do that by using a factory that can ingest a .ini file and
        // return a SecurityManager instance:

        // Use the shiro.ini file at the root of the classpath
        // (file: and url: prefixes load from files and urls respectively):
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();

        // for this simple example quickstart, make the SecurityManager
        // accessible as a JVM singleton.  Most applications wouldn't do this
        // and instead rely on their container configuration or web.xml for
        // webapps.  That is outside the scope of this simple quickstart, so
        // we'll just do the bare minimum so you can continue to get a feel
        // for things.
        SecurityUtils.setSecurityManager(securityManager);

        // Now that a simple Shiro environment is set up, let's see what you can do:

        // get the currently executing user:
        Subject currentUser = SecurityUtils.getSubject();

        // Do some stuff with a Session (no need for a web or EJB container!!!)
        Session session = currentUser.getSession();
        session.setAttribute("someKey", "someValue");
        String value = (String) session.getAttribute("someKey");
        if ("someValue".equals(value)) {
            log.info("Retrieved the correct value! [{}]", value);
        }

        // let's login the current user so we can check against roles and permissions:
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken("trump", "pass_unreliable");
            token.setRememberMe(true);
            try {
                currentUser.login(token);
            } catch (UnknownAccountException uae) {
                log.info("There is no user with username of {}", token.getPrincipal());
            } catch (IncorrectCredentialsException ice) {
                log.info("Password for account {} was incorrect!", token.getPrincipal());
            } catch (LockedAccountException lae) {
                log.info("The account for username {} is locked. Please contact your administrator to unlock it.",
                        token.getPrincipal());
            }
            // ... catch more exceptions here (maybe custom ones specific to your application?
            catch (AuthenticationException ae) {
                //unexpected condition?  error?
            }
        }

        //say who they are:
        //print their identifying principal (in this case, a username):
        log.info("User [{}] logged in successfully.", currentUser.getPrincipal());

        //test a role:
        if (currentUser.hasRole("president_us_role")) {
            log.info("May the president_us_role be with you!");
        } else {
            log.info("Let`s make America great again!");
        }

        //test a typed permission (not instance-level)
        if (currentUser.isPermitted("usa:whitehouse:*")) {
            log.info("You can work here");
        } else {
            log.info("Please move in the White House.");
        }

        //a (very powerful) Instance Level permission:
        if (currentUser.isPermitted("villa:serve:labor1")) {
            log.info("You have labors to work you.");
        } else {
            log.info("Sorry, you lost your money.");
        }

        //all done - log out!
        currentUser.logout();

        System.exit(0);
    }
}

package br.ufes.inf.nemo.marvin.core.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;
import br.ufes.inf.nemo.marvin.core.application.ChangePasswordService;
import br.ufes.inf.nemo.marvin.core.domain.Academic;
import br.ufes.inf.nemo.marvin.core.exceptions.InvalidPasswordCodeException;
import br.ufes.inf.nemo.marvin.core.exceptions.OperationFailedException;

/**
 * TODO: document this type.
 *
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
@Named
@ConversationScoped
public class ChangePasswordController extends JSFController {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(ChangePasswordController.class.getCanonicalName());

	/** The JSF conversation. */
	@Inject
	private Conversation conversation;

	/** The "Change Password" service. */
	@EJB
	private ChangePasswordService changePasswordService;

	/** TODO: document this field. */
	private String passwordCode;

	/** TODO: document this field. */
	private boolean validCode;
	
	/** TODO: document this field. */
	private Academic academic;
	
	/** TODO: document this field. */
	private String password;
	
	/** TODO: document this field. */
	private String repeatPassword;

	/** Getter for academic. */
	public Academic getAcademic() {
		return academic;
	}

	/** Getter for passwordCode. */
	public String getPasswordCode() {
		return passwordCode;
	}

	/** Setter for passwordCode. */
	public void setPasswordCode(String passwordCode) {
		this.passwordCode = passwordCode;
	}

	/** Getter for validCode. */
	public boolean isValidCode() {
		return validCode;
	}

	/** Getter for password. */
	public String getPassword() {
		return password;
	}

	/** Setter for password. */
	public void setPassword(String password) {
		this.password = password;
	}

	/** Getter for repeatPassword. */
	public String getRepeatPassword() {
		return repeatPassword;
	}

	/** Setter for repeatPassword. */
	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	/**
	 * TODO: document this method.
	 */
	public void checkCode() {
		// First checks if a password code was given at all, then checks if it's valid.
		validCode = passwordCode != null;
		if (validCode) try {
			// Attempts to find the academic with the given password code.
			academic = changePasswordService.retrieveAcademicByPasswordCode(passwordCode);

			// If all went well, begins the conversation, dropping any previous conversation, if existing.
			logger.log(Level.FINEST, "Beginning conversation. Current conversation transient? -> {0}", new Object[] { conversation.isTransient() });
			if (!conversation.isTransient()) conversation.end();
			conversation.begin();			
		}
		
		// If an academic could not be found, deem the code invalid.
		catch (InvalidPasswordCodeException e) {
			validCode = false;
		}
	}
	/**
	 * Checks if both password fields have the same value.
	 * 
	 * This method is intended to be used with AJAX.
	 */
	public void ajaxCheckPasswords() {
		checkPasswords();
	}

	/**
	 * Checks if the contents of the password fields match.
	 * 
	 * @return <code>true</code> if the passwords match, <code>false</code> otherwise.
	 */
	private boolean checkPasswords() {
		if (((repeatPassword != null) && (!repeatPassword.equals(password))) || ((repeatPassword == null) && (password != null))) {
			logger.log(Level.INFO, "Password and repeated password are not the same");
			addGlobalI18nMessage("msgsCore", FacesMessage.SEVERITY_WARN, "changePassword.error.passwordsDontMatch.summary", "changePassword.error.passwordsDontMatch.detail");
			return false;
		}
		return true;
	}
	
	public String changePassword() {
		logger.log(Level.FINEST, "Changing password for academic {0} (password code {1})", new Object[] { academic, passwordCode });
		
		// Changes the password.
		try {
			changePasswordService.changePassword(passwordCode, password);
		}
		catch (OperationFailedException e) {
			logger.log(Level.SEVERE, "Change password threw exception", e);
			addGlobalI18nMessage("msgsCore", FacesMessage.SEVERITY_FATAL, "changePassword.error.operationFailed.summary", "changePassword.error.operationFailed.detail");
			return null;
		}

		// If the code is suddenly invalid, send the user back to start.
		catch (InvalidPasswordCodeException e) {
			validCode = false;
		}

		// Ends the conversation.
		logger.log(Level.FINEST, "Ending conversation. Current conversation transient? -> {0}", new Object[] { conversation.isTransient() });
		if (!conversation.isTransient()) conversation.end();

		// Redirects to the conclusion if everything went fine. 
		if (validCode) return "done.xhtml";
		return null;
	}
}

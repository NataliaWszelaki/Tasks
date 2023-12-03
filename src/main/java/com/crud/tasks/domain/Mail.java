package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class Mail {
    private final String mailTo;
    private final String toCc;
    private final String subject;
    private final String message;

    public static class MailBuilder {

        private String mailTo;
        private String toCc;
        private String subject;
        private String message;

        public MailBuilder mailTo(String mailTo) {
            this.mailTo = mailTo;
            return this;
        }

        public MailBuilder toCc(String toCc) {
            this.toCc = toCc;
            return this;
        }

        public MailBuilder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public MailBuilder message(String message) {
            this.message = message;
            return this;
        }

        public Mail build() {
            return new Mail(mailTo, toCc, subject, message);
        }
    }
}
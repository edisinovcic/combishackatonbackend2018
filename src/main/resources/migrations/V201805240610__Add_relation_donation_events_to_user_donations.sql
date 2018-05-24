ALTER TABLE postgres.codeapp.donation_events ADD user_donation_id VARCHAR(255) REFERENCES postgres.codeapp.donation_events(id);
ALTER TABLE postgres.codeapp.user_donations ADD donation_event_id VARCHAR(255) REFERENCES postgres.codeapp.user_donations(id);

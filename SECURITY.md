# Security and data-safety guidance

This is an educational CRUD application, not an audited administration service. No supported-release programme or reporting deadline is established here.

## Immediate boundaries

- Normal startup drops products/users with CASCADE and recreates them. Use a disposable database. The overlay does not change this behaviour.
- Database credentials are committed. Rotate any live values and review historical copies. No credential values are reproduced in these documents.
- No application login, authorisation or CSRF protection implementation is supplied. Role and active fields do not enforce access permissions. Do not use real personal records or expose the demo as a protected admin tool.
- Forms bind directly to JPA entities. Review server-controlled fields and allowed binding before public use; validation is not an ownership check.
- Controllers display exception messages. Review error redaction and DEBUG/SQL logging before handling private data.
- Image URLs cause browser requests to supplied destinations. Review allowed schemes/hosts and privacy requirements separately.

The POM's dependency-safety comments are not audit evidence. No vulnerability scan, exploit or dependency compatibility test was performed.

The ignore rules do not explicitly exclude a plain .env file. Review staged files before adding private configuration. For sensitive reports, use a verified private maintainer channel if available, or request one without publishing details. No dedicated reporting address or enabled private-reporting feature was confirmed.

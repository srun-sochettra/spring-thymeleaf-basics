# Contributing

Keep changes focused on the existing MVC/service/JPA structure. Use synthetic records and a disposable database: normal startup drops and recreates the product/user tables.

Run wrapper checks and report actual results. The context smoke test is not CRUD or browser coverage and is not configured with an isolated database. Review DataInitializer before running it.

Useful regression cases include invalid forms preserving input, duplicate emails, missing IDs, status toggles, keyword/category precedence and inactive-record visibility. Check crafted form fields as well as normal UI submissions because controllers bind entities directly.

For browser changes, check keyboard focus, modal close/return behaviour, long tables, failed images, large text, reduced motion and external-resource failures. Bootstrap markup alone does not establish accessibility.

Describe the problem, focused change, checks, schema/data impact and rollback. Keep credentials, private records and generated target files out of commits. Follow [SECURITY.md](SECURITY.md).

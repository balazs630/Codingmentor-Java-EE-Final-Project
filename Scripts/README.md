# Project-and-Task Handler

Table of Contents
=================

  * [Project-and-Task Handler](#project-and-task-handler)
    * [Scripts](#scripts)
      * [start.sh](#startsh)
        * [Steps](#steps)
      * [sql-init.sh](#sql-initsh)
        * [Steps](#steps-1)
        * [Options](#options)
      * [init.sh](#initsh)
        * [Steps](#steps-2)
        * [Options](#options-1)

## Scripts

### start.sh

#### Steps
* execute ./sql-init.sh
* if no errors found, execute ./init.sh

### sql-init.sh

#### Steps
* Check for running processes
* Check ports
* Download, initialize, and start the SQL server

#### Options

```
-h	show help
-q	quiet, non-interactive mode
-f	force, skip checks
```

### init.sh

#### Steps
* Check for running processes
* Check ports
* Download, and start Wildfly (SQL server **must be running**)
* Download, and deploy mysql-connector
* Build and deploy project

#### Options

```
-h	show help
-q	quiet, non-interactive mode
-f	force, skip checks
```
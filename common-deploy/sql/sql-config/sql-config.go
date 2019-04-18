package main

import (
	"flag"
	"fmt"
	"os"
	"strings"

	"github.com/go-sql-driver/mysql"
	"github.com/mitchellh/cli"
)

func main() {
	os.Exit(realMain())
}

var ui cli.Ui

func realMain() int {
	ui = &cli.BasicUi{Writer: os.Stdout}
	cli := &cli.CLI{
		Args:     os.Args[1:],
		HelpFunc: cli.BasicHelpFunc("sql-config"),
		Version:  "1.0.0",
	}
	exitCode := run(cli.Args)
	return exitCode
}

func run(args []string) int {

	cmdFlags := flag.NewFlagSet("", flag.ContinueOnError)
	ConfigFlags(cmdFlags)

	if err := cmdFlags.Parse(args); err != nil {
		return 1
	}

	env, err := GetEnvironment()
	if err != nil {
		ui.Error(err.Error())
		return 1
	}

	c, err := mysql.ParseDSN(env.DataSource)
	if err != nil {
		ui.Error(err.Error())
		return 1
	}
	ipport := strings.Split(c.Addr, ":")
	fmt.Fprintf(os.Stdout, "Net %s\n", c.Net)
	fmt.Fprintf(os.Stdout, "Addr %s\n", c.Addr)
	fmt.Fprintf(os.Stdout, "IP %s\n", ipport[0])
	fmt.Fprintf(os.Stdout, "Port %s\n", ipport[1])
	fmt.Fprintf(os.Stdout, "User %s\n", c.User)
	fmt.Fprintf(os.Stdout, "Passwd %s\n", c.Passwd)
	fmt.Fprintf(os.Stdout, "DBName %s\n", c.DBName)

	return 0
}

package main

import (
    "fmt"
    "runtime"
    "net/http"
    _"net/http/pprof"
    "github.com/DeanThompson/ginpprof"
    "github.com/gin-gonic/gin"
)

func MainRouter(ctx *gin.Context) {
    ctx.String(http.StatusOK, "OK")
}

func main() {
	fmt.Println("vim-go")
    runtime.GOMAXPROCS(runtime.NumCPU())

    router := gin.Default()
    router.Get("/", MainRouter)

    ginpprof.Wrapper(router)
    router.Run()
}

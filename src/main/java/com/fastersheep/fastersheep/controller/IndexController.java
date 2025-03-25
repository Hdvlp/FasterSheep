package com.fastersheep.fastersheep.controller;

import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;



@RestController
public class IndexController {

    @GetMapping("/")
    public String indexPage(HttpServletRequest request) {
        //return new String("Hello here..." + request.getSession().getId());
        return new String("""
            <!DOCTYPE html>
                <html><head><meta charset="UTF-8"/>
                <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
                <style>
                    body {
                        font-size: 18px;
                    }
                    #question{
                        padding: 10px;
                    }
                    #logout { 
                        display: flex; 
                        justify-content: flex-end;
                    }
                    #logout > div { 
                        padding: 10px;
                    }
                    #buttons {
                        padding: 10px;
                    }
                    #data {
                        display: flex; 
                        flex-direction: column;
                        margin: 10px;
                    }

                    #data > div {
                        background:#fff; 
                        display: grid; 
                        grid-template-columns: auto auto auto;
                        border: 1px solid black;
                        grid-gap: 1px;
                    }
  
  
                    #data > div {
                        background-color: white;
                        padding: 15px;
                        text-align: center;
                    }
                    button {
                        padding: 10px; 
                        font-size: 15px;
                    }
                </style>
                </head>
                <body>
                    <div id="question">How much is 1 Bitcoin in ___? </div>
                    <div id="logout"><div><a href="/logout">Log out</a></div></div>
                    <div id="buttons">
                        <button id="usd">USD</button>
                        <button id="jpy">JPY</button>
                        <button id="cny">CNY</button>
                        <button id="hkd">HKD</button>
                        <button id="gbp">GBP</button>
                    </div>
                    <div id="data"></div>
                    <script>


                            document.onreadystatechange = () => {
                                if (document.readyState === "complete") {

                            const main = async function(curr){
                            const dd = document.getElementById("data");
                            dd.innerHTML = "";
                            const url = "/api/show/g?currencies=" + curr;
                                try {
                                    const response = await fetch(url);
                                    if (!response.ok) {
                                    throw new Error(`Response status: ${response.status}`);
                                    }

                                    const text = await response.text();
                                    dd.innerHTML = text;
                                } catch (error) {
                                    console.error(error.message);
                                }
                            }


                                    main("usd");
                            
                                    function setButton(curr){
                                        const el = document.getElementById(curr);
                                        el.addEventListener("click", function(){ main(curr) });
                                    }
                                    setButton("usd");
                                    setButton("cny");
                                    setButton("jpy");
                                    setButton("hkd");
                                    setButton("gbp");
                                    
                                }
                            };


                        
                    </script>
                </body>
                </html>
            """
                    
                    );
    }




}

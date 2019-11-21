sen="""<items>
<item>
<nodeid>NAT013747</nodeid>
<nodename>상동</nodename>
</item>
<item>
<nodeid>NAT013841</nodeid>
<nodename>밀양</nodename>
</item>
<item>
<nodeid>NAT013967</nodeid>
<nodename>삼랑진</nodename>
</item>
<item>
<nodeid>NAT014058</nodeid>
<nodename>원동</nodename>
</item>
<item>
<nodeid>NAT014150</nodeid>
<nodename>물금</nodename>
</item>
<item>
<nodeid>NAT880099</nodeid>
<nodename>한림정</nodename>
</item>
<item>
<nodeid>NAT880177</nodeid>
<nodename>진영</nodename>
</item>
<item>
<nodeid>NAT880179</nodeid>
<nodename>진례</nodename>
</item>
<item>
<nodeid>NAT880281</nodeid>
<nodename>창원중앙</nodename>
</item>
<item>
<nodeid>NAT880310</nodeid>
<nodename>창원</nodename>
</item>
</items>
<numOfRows>10</numOfRows>"""

a=sen.split("\n")

for v in a:
    if(v[0:8]=="<nodeid>"):
        mina=v[8:]
        k=mina.split("</")
        print('"'+k[0]+'",', end=" ")

print();

for v in a:
    if(v[0:10]=="<nodename>"):
        mina=v[10:]
        k=mina.split("</")
        print('"'+k[0]+'",', end=" ")

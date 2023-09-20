Public Class Form1
    Dim str, str2, fr1, fr2, cnt As String 'str=使用者輸入的 str2=答案

    Private Sub Button1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button1.Click '確定要猜的號碼r
        If TextBox1.TextLength = 4 Then
            str = TextBox1.Text
            Dim ck As Integer
            Dim n(3) As String
            cker(n, str, ck)
            fr1 = 0 : fr2 = 0
            If ck = 0 Then
                cnt += 1
                Button5.Enabled = True
                For i As Integer = 0 To 3
                    For x As Integer = 0 To 3
                        If str(i) = str2(x) Then
                            If i = x Then
                                fr1 += 1
                            Else
                                fr2 += 1
                            End If
                        End If
                    Next
                Next
                If fr1 = 4 Then
                    Label6.Text = "總共花了：" & cnt & "次"
                    Label6.Visible = True
                    Label4.Visible = True : Label5.Visible = True
                    TextBox1.Enabled = False : Button1.Enabled = False : Button4.Enabled = False
                    MsgBox("恭喜答對", , "恭喜") : Beep()
                End If
                TextBox2.Text += str & "   " & fr1 & "A" & fr2 & "B" & vbNewLine
            Else
                MsgBox("輸入錯誤", , "錯誤")
                TextBox1.Text = "" : TextBox1.Focus()
            End If
        Else
            MsgBox("請輸入四個數字", , "輸入錯誤") : TextBox1.Text = "" : TextBox1.Focus()
        End If
    End Sub

    Private Sub Form1_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        clear()
    End Sub

    Private Sub Button2_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button2.Click '確定最後答案的確定
        If TextBox3.TextLength = 4 Then
            If TextBox3.Text = "" Then
                MsgBox("請輸入答案", , "輸入錯誤")
            Else
                str2 = TextBox3.Text
                TextBox1.Enabled = True : Button1.Enabled = True
                Me.Height = 308
                TextBox3.Text = "" : TextBox3.Enabled = False : Button2.Enabled = False : Button3.Enabled = False : Button4.Enabled = True
                Label5.Text = str2
            End If
        Else
            MsgBox("請輸入四個數字", , "輸入錯誤") : TextBox3.Text = "" : TextBox3.Focus()
        End If
    End Sub

    Private Sub TextBox1_KeyPress(ByVal sender As System.Object, ByVal e As System.Windows.Forms.KeyPressEventArgs) Handles TextBox1.KeyPress
        If Not (Asc(e.KeyChar) >= 48 And Asc(e.KeyChar) <= 57 Or Asc(e.KeyChar) = 8) Then e.Handled = True
    End Sub

    Private Sub 設定ToolStripMenuItem_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles 設定ToolStripMenuItem.Click
        Me.Height = 422
        TextBox3.Focus()
    End Sub

    Private Sub Button4_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button4.Click '放棄
        Button1.Enabled = False : TextBox1.Enabled = False : Button5.Enabled = True : Button4.Enabled = False
        Label4.Visible = True
        Label5.Visible = True
        Label5.Text = str2
    End Sub

    Private Sub Button5_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button5.Click '再來一次
        clear()
    End Sub

    Private Sub Button3_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button3.Click '隨機
        Randomize()
        Button4.Enabled = True : TextBox3.Enabled = False : Button2.Enabled = False : Me.Height = 308 : Button1.Enabled = True : Button5.Enabled = True : TextBox1.Enabled = True
        Button3.Enabled = False
        Dim ck As Integer
        Dim n(3) As String
        str2 = Int((Rnd() * 8999) + 1000)
        cker(n, str2, ck)
        Do While ck = 1
            str2 = Int((Rnd() * 8999) + 1000)
            cker(n, str2, ck)
        Loop
        Label5.Text = str2
    End Sub

    Sub cker(ByVal n() As String, ByVal st As String, ByRef ck As Integer)
        For i As Integer = 0 To 3
            n(i) = Mid(st, i + 1, 1)
            For x As Integer = 0 To i
                If x <> i Then
                    If n(i) = n(x) Then
                        ck = 1 : Exit Sub
                    Else
                        ck = 0
                    End If
                End If
            Next
        Next
    End Sub

    Sub clear()
        cnt = 0
        Label6.Visible = False
        TextBox3.Text = ""
        Button4.Enabled = False
        Button3.Enabled = True
        Button5.Enabled = False
        Button1.Enabled = False
        str = "" : str2 = ""
        Label4.Visible = False
        Label5.Visible = False
        Me.Height = 308
        TextBox3.Enabled = True
        Button2.Enabled = True
        Button3.Enabled = True
        TextBox1.Text = "" : TextBox2.Text = ""
    End Sub

    Private Sub TextBox3_KeyPress(ByVal sender As System.Object, ByVal e As System.Windows.Forms.KeyPressEventArgs) Handles TextBox3.KeyPress
        If Not (Asc(e.KeyChar) >= 48 And Asc(e.KeyChar) <= 57 Or Asc(e.KeyChar) = 8) Then e.Handled = True
    End Sub

    Private Sub TextBox2_KeyPress(ByVal sender As System.Object, ByVal e As System.Windows.Forms.KeyPressEventArgs) Handles TextBox2.KeyPress
        e.Handled = True
    End Sub

    Private Sub 結束AltXToolStripMenuItem_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles 結束AltXToolStripMenuItem.Click
        End
    End Sub
End Class

<nav class="navbar navbar-expand-lg navbar-light no-padding" style="background-color: white;">

  <!-- Just an image -->
  <a class="navbar-brand" href="#">
    <img src="image/logo.png" height="50" alt="方糖简历logo">
  </a>

  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse justify-content-end" id="navbarNavDropdown">
    <?php
    function active_class( $link )
    {
        if( $link == ltrim( $_SERVER['SCRIPT_NAME'] , '/' ) ){
            return " active ";
        }
    }
    ?>
    <?php if( $is_login ): ?>
    <ul class="navbar-nav">
        <li class="nav-item <?=active_class('resume_list.new.php')?>"><a href="resume_list.new.php" class="nav-link"><span class="menu-square"></span>我的简历</a></li>
        <li class="nav-item <?=active_class('user_logout.php')?>"><a href="user_logout.php" class="nav-link"><span class="menu-square"></span>退出</a></li>
    </ul>
    <?php else: ?>
    <ul class="navbar-nav">
        <li class="nav-item <?=active_class('user_reg.new.php')?>"><a href="user_reg.new.php" class="nav-link"><span class="menu-square"></span>注册</a></li>
        <li class="nav-item <?=active_class('user_login.new.php')?>"><a href="user_login.new.php" class="nav-link"><span class="menu-square"></span>登入</a></li>
    </ul>
    <?php endif; ?>
  </div>
</nav>
package com.cos.blog.test;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Supplier;

@RestController
public class DummyControllerTest {

    @Autowired // 의존성 주입.
    private UserRepository userRepository;

    @DeleteMapping("/dummy/user/{id}")
    public String deleteUser(@PathVariable int id){
        try{
            userRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            return "삭제에 실패! 해당 id가 없습니다.";
        }

        return "삭제 되었습니다. id="+id;
    }


    // save() 함수는 id를 전달하지 않으면 insert
    // save() 함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update
    // save() 함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert
    @Transactional // 함수 종료시에 자동 commit이 됨.
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id,@RequestBody User requestUser){
        System.out.println("id : "+id);
        System.out.println("password : "+requestUser.getPassword());
        System.out.println("email : "+requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("수정에 실패하였습니다.");
        });

        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

//        userRepository.save(user);

        // 더티 체킹
        return user;
    }

    @GetMapping("/dummy/users")
    public List<User> list(){
        return userRepository.findAll();
    }

    @GetMapping("/dummy/user")
    public Page<User> pageList(@PageableDefault(size =2, sort="id", direction = Sort.Direction.DESC) Pageable pageable){
//        Page<User> users= userRepository.findAll(pageable);
//        List<User> users= userRepository.findAll(pageable).getContent();
        Page<User> pagingUser= userRepository.findAll(pageable);
        List<User> users = pagingUser.getContent();
        return pagingUser;
    }


    // {id} 주소로 파라미터를 전달받을 수 있음.
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id){
        // user/100을 찾으면 내가 DB에서 못찾아오게 되면 user가 null이 될 것 아냐?
        // 그럼 return null이 리턴 되잖아.. 그럼 프로그램에 문제가 있지 않겠니..?
        // Optional로 너의 User객체를 감써서 가져올테니 null인지 아닌지 판단해서 return
        // 람다식 x
        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 유저는 없습니다.");
            }
        });

        
        // 람다식
//        User user = userRepository.findById(id).orElseThrow(()-> {
//            return new IllegalArgumentException("해당 유저는 없습니다.");
//        });

        // 요청 : 웹 브라우저
        // user 객체 = 자바 오브젝트
        // 변환(웹 브라우저가 이해할 수 있는 데이터)-> json(Gson라이브러리)
        // 스프링부트 = MessageConverter라는 애가 응답시에 자동 작동
        // 만약에 자바 오브젝트를 리턴하게 되면 MessgaeConverter가 Jckson 라이브러리를 호출해서
        // user 오브젝트를 json으로 변환해서 브라우저에게 던져준다.

        return user;
    }


    // http://loacalhost:8080/dummy/join (요청)
    // http의 body에 username, password, email 데이터를 가지고 요청
    @PostMapping("/dummy/join")
    public String join(User user){
        System.out.println("id :"+user.getId());
        System.out.println("username :"+user.getUsername());
        System.out.println("password :"+user.getPassword());
        System.out.println("email :"+user.getEmail());

        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입이 완료되었습니다.";
    }
}

package com.resourceit.app.views;


import android.view.View;

import com.resourceit.app.R;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.util.TreeIterables;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;


public class MainActivityTest {


    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    private String cpfValido = "41704310067";
    private String cpfInValido = "41704310060";
    private String EmailValido = "user@domain.ltd";
    private String EmailInValido = "user@domain";
    private String SenhaValida = "aaAA12@#";
    private String SenhaInValida1 = "aaAA12@";
    private String SenhaInValida2 = "aaAA1200";
    private String SenhaInValida3 = "aaAA1234";
    private String SenhaInValida4 = "abcd12@@";

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void Test_Cpf_E_Senha_Valido(){
        onView(withId(R.id.loginLaout)).check(matches(isDisplayed()));
        onView(withId(R.id.user)).perform(replaceText("")).perform(typeText(cpfValido));
        onView(withId(R.id.password)).perform(replaceText("")).perform(typeText(SenhaValida));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.login)).perform(click());
        onView(isRoot()).perform(waitId(R.id.statmentsLayout, TimeUnit.SECONDS.toMillis(15))).check(matches(isDisplayed()));
    }

    @Test
    public void Test_Email_E_Senha_Valido(){
        onView(withId(R.id.loginLaout)).check(matches(isDisplayed()));
        onView(withId(R.id.user)).perform(replaceText("")).perform(typeText(EmailValido));
        onView(withId(R.id.password)).perform(replaceText("")).perform(typeText(SenhaValida));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.login)).perform(click());
        onView(isRoot()).perform(waitId(R.id.statmentsLayout, TimeUnit.SECONDS.toMillis(15))).check(matches(isDisplayed()));
    }

    @Test
    public void Test_Cpf_InValido(){
        onView(withId(R.id.loginLaout)).check(matches(isDisplayed()));
        onView(withId(R.id.user)).perform(replaceText("")).perform(typeText(cpfInValido));
        onView(withId(R.id.password)).perform(replaceText("")).perform(typeText(SenhaValida));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.login)).perform(click());
        onView(isRoot()).perform(waitId(R.id.statmentsLayout, TimeUnit.SECONDS.toMillis(5))).check(matches(not(doesNotExist())));
    }

    @Test
    public void Test_Email_InValido(){
        onView(withId(R.id.loginLaout)).check(matches(isDisplayed()));
        onView(withId(R.id.user)).perform(replaceText("")).perform(typeText(EmailInValido));
        onView(withId(R.id.password)).perform(replaceText("")).perform(typeText(SenhaValida));
        Espresso.closeSoftKeyboard();
        onView(isRoot()).perform(waitId(R.id.login, TimeUnit.SECONDS.toMillis(5))).perform(click());
        onView(isRoot()).perform(waitId(R.id.statmentsLayout, TimeUnit.SECONDS.toMillis(5))).check(matches(not(doesNotExist())));
    }

    @Test
    public void Test_Senha_InValido_01(){
        onView(withId(R.id.loginLaout)).check(matches(isDisplayed()));
        onView(withId(R.id.user)).perform(replaceText("")).perform(typeText(cpfValido));
        onView(withId(R.id.password)).perform(replaceText("")).perform(typeText(SenhaInValida1));
        Espresso.closeSoftKeyboard();
        onView(isRoot()).perform(waitId(R.id.login, TimeUnit.SECONDS.toMillis(5))).perform(click());
        onView(isRoot()).perform(waitId(R.id.statmentsLayout, TimeUnit.SECONDS.toMillis(5))).check(matches(not(doesNotExist())));
    }

    @Test
    public void Test_Senha_InValido_02(){
        onView(withId(R.id.loginLaout)).check(matches(isDisplayed()));
        onView(withId(R.id.user)).perform(replaceText("")).perform(typeText(cpfValido));
        onView(withId(R.id.password)).perform(replaceText("")).perform(typeText(SenhaInValida2));
        Espresso.closeSoftKeyboard();
        onView(isRoot()).perform(waitId(R.id.login, TimeUnit.SECONDS.toMillis(5))).perform(click());
        onView(isRoot()).perform(waitId(R.id.statmentsLayout, TimeUnit.SECONDS.toMillis(5))).check(matches(not(doesNotExist())));
    }

    @Test
    public void Test_Senha_InValido_03(){
        onView(withId(R.id.loginLaout)).check(matches(isDisplayed()));
        onView(withId(R.id.user)).perform(replaceText("")).perform(typeText(cpfValido));
        onView(withId(R.id.password)).perform(replaceText("")).perform(typeText(SenhaInValida3));
        Espresso.closeSoftKeyboard();
        onView(isRoot()).perform(waitId(R.id.login, TimeUnit.SECONDS.toMillis(5))).perform(click());
        onView(isRoot()).perform(waitId(R.id.statmentsLayout, TimeUnit.SECONDS.toMillis(5))).check(matches(not(doesNotExist())));
    }

    @Test
    public void Test_Senha_InValido_04(){
        onView(withId(R.id.loginLaout)).check(matches(isDisplayed()));
        onView(withId(R.id.user)).perform(replaceText("")).perform(typeText(cpfValido));
        onView(withId(R.id.password)).perform(replaceText("")).perform(typeText(SenhaInValida4));
        Espresso.closeSoftKeyboard();
        onView(isRoot()).perform(waitId(R.id.login, TimeUnit.SECONDS.toMillis(5))).perform(click());
        onView(isRoot()).perform(waitId(R.id.statmentsLayout, TimeUnit.SECONDS.toMillis(5))).check(matches(not(doesNotExist())));
    }

    @After
    public void tearDown() throws Exception {
    }



    public static ViewAction waitId(final int viewId, final long millis) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "wait for a specific view with id <" + viewId + "> during " + millis + " millis.";
            }

            @Override
            public void perform(final UiController uiController, final View view) {
                uiController.loopMainThreadUntilIdle();
                final long startTime = System.currentTimeMillis();
                final long endTime = startTime + millis;
                final Matcher<View> viewMatcher = withId(viewId);

                do {
                    for (View child : TreeIterables.breadthFirstViewTraversal(view)) {
                        // found view with required ID
                        if (viewMatcher.matches(child)) {
                            return;
                        }
                    }

                    uiController.loopMainThreadForAtLeast(50);
                }
                while (System.currentTimeMillis() < endTime);

                // timeout happens
                /*throw new PerformException.Builder()
                        .withActionDescription(this.getDescription())
                        .withViewDescription(HumanReadables.describe(view))
                        .withCause(new TimeoutException())
                        .build();*/
            }
        };
    }


}